process salmon_index {
		

		label 'bigCPU'
		publishDir "${params.outdir}/salmon/salmon_index/", mode: 'copy'
    		
		input:
		path fasta
		path gtf
		
		output:
		path "salmon_index/*" 
		
		"""
		set +u; source activate pipeone_nm; set -u
		gffread ${gtf} -g ${fasta} -w transcripts.fa -W
		salmon index -t transcripts.fa -i salmon_index -p $task.cpus
		"""
}

process salmon {
	label 'bigCPU'
	

	publishDir "${params.outdir}/salmon/samples", mode: 'copy'
		
	tag {id }

	input:
	tuple val(id), path(reads)
	path "transcripts_index/*" 
	
	output:
	path "${id}"  
		
	script:
	
	"""
	set +u; source activate pipeone_nm; set -u
	salmon quant -i transcripts_index -l A -1 ${reads[0] } -2 ${reads[1] } -p $task.cpus -o ${id} 

	"""
	
	
}

process salmon_merge {
	
	
	publishDir "${params.outdir}/salmon/", mode: 'copy'
	
	input:
	path "ref_gtf" 
	path "samples/*" 
	
	output:
	path "salmon_gene_est_counts.tsv", emit: counts
	
	path "salmon_gene_tpm.tsv", emit: TPM
	path "*tsv"
	path("Transcripts.fasta.gene_trans_map"), emit: gene_trans_map
	
	
	"""
	set +u; source activate pipeone_nm; set -u
	
	python3 ${baseDir}/bin/lncRNA.py get-txID-geneID ref_gtf protein_coding_and_all_lncRNA.txID_geneID.tsv
	awk '{print \$2,\$1}' OFS="\t" protein_coding_and_all_lncRNA.txID_geneID.tsv > Transcripts.fasta.gene_trans_map
	Rscript ${baseDir}/bin/tximport_salmon.R
	
	"""
}


workflow Salmon_idx_cal {
	take:
	reads
	genome
	gtf

	main:
	salmon_index(genome, gtf)
	salmon(reads, salmon_index.out.collect() )
	salmon_merge(gtf, salmon.out.collect() )

	emit:
	
	counts = salmon_merge.out.counts
	TPM    = salmon_merge.out.TPM
	gene_trans_map = salmon_merge.out.gene_trans_map

}

