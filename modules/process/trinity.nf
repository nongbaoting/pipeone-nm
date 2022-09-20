
process Align_and_estimate_abundance {
        lable "bigCPU"

        publishDir "${params.outdir}/aea", mode: 'copy',
            saveAs: {filename -> "${filename}"}
                
        
        input:
        
        path("Transcripts.fasta")
        tuple val(id), path(reads)
        
        output:
        path("output_dir/*"), emit: outdir
        path("Transcripts.fasta.gene_trans_map"), emit: gene_trans_map
        
        
        script:

        """
        python3 ${baseDir}/bin/create_gene_trans_map.py
        
        align_and_estimate_abundance.pl --transcripts Transcripts.fasta --seqType fq --est_method salmon \\
        --left "${reads[0]}" --right "${reads[1]}" --gene_trans_map Transcripts.fasta.gene_trans_map --thread_count ${task.cpus } \\
        --prep_reference --output_dir output_dir > align_and_estimate_abundance.log 2>&1
        
        """
}


process Abundance_estimates_to_matrix {
	

        publishDir "${params.outdir}/aem", mode: 'copy',
            saveAs: {filename ->
            if (filename =~/gene/) "gene/${filename}"
            else if (filename =~/isoform/) "isoform/${filename}"
            else null
            }
        
        input:
        path("outdir/*")
        path "Transcripts.fasta.gene_trans_map"
        

        output:
        path "Transcripts.gene.counts.matrix"
        path "Transcripts.gene.TPM.not_cross_norm"
        path "Transcripts.isoform.counts.matrix"
        path "Transcripts.isoform.TPM.not_cross_norm"
        
        
        script:

        """
        abundance_estimates_to_matrix.pl --est_method salmon \\
        --gene_trans_map Transcripts.fasta.gene_trans_map --out_prefix Transcripts \\
        --name_sample_by_basedir outdir/quant.sf 2> matrix.log
        
        """
}
