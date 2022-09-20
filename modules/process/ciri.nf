
ciri_path = params.ciri_path
process Ciri {
	tag {id}	
        label "medianCPU"
        label 'bigMEM'
        publishDir "${params.outdir}/ciri/", mode: 'copy',
            saveAs: {filename -> 
                if (filename =~/ciri/) "ciri/${filename}"
                
                else null
                }
        
        input:
        path("genomic.fna")
        path("assembly.gtf")
        tuple val(id), path("${id}.sam")
        
        
        
        output:
        tuple val(id), path("${id}.ciri"), emit: ciri
       
        
        script:

        """
        perl ${ciri_path}/bin/CIRI_v2.0.6/CIRI2.pl -T ${task.cpus } -I ${id}.sam \\
        -O ${id}.ciri -F genomic.fna -A assembly.gtf
        
        
        """
}

process CiriAs {
        
	tag {id}	
        publishDir "${params.outdir}/ciri/", mode: 'copy',
            saveAs: {filename -> 
                if (filename =~/as/) "ciri_as/${filename}"
                
                else null
                }
        
        input:
        path("genomic.fna")
        path("assembly.gtf")
        tuple val(id), path("${id}.sam"),  path(ciri)
        
        
        output:
        tuple val(id), path("${id}.as*"), emit: cirias
        
        
        
        
        script:

        """
        perl ${ciri_path}/bin/CIRI_AS_v1.2/CIRI_AS_v1.2.pl -D yes \\
        -S ${id}.sam -O ${id}.as -C ${ciri} -F genomic.fna -A assembly.gtf
        
        """
}


process CIRIvis {
	tag {id}	
        publishDir "${params.outdir}/ciri/", mode: 'copy',
            saveAs: {filename -> "cirivis/${filename}"}
        
        input:
        tuple val(id), path("cirias/*")
        path("genomic.fna")
        
        output:
        path("${id}/*")
        
        
        
        
        script:

        """
        java -jar ${ciri_path}/CIRI-vis.jar -i cirias/${id}.as_jav.list \\
        -l cirias/${id}.as_library_length.list -d ${id} \\
        -r genomic.fna -type pdf -o ${id}

        
        """
}

process CIRIquant {
	tag {id}
        label "medianCPU"
        label 'bigMEM'
        publishDir "${params.outdir}/ciri/", mode: 'copy',
            saveAs: {filename -> "ciriquant/${filename}"}
        
        input:
        tuple val(id), path(bam), path(reads), path(ciri)
       
        path("genomic.fna")
        path("assembly.gtf")
        path("*")
        path("*")
        
        
        output:
        tuple val(id), path("${id}/*"), emit: ciriquant
        path("${id}") , emit: ciriquant_res
        
        
        
        script:

        """
        set +u; source activate pipeone_nm; set -u
	echo "name: example
	tools:
	  bwa: \$(which bwa)
	  hisat2:  \$(which hisat2)
	  stringtie: \$(which stringtie)
	  samtools: \$(which samtools)

	reference:
	  fasta: genomic.fna
	  gtf: assembly.gtf
	  bwa_index: genomic.fna
	  hisat_index: genomic
	" >config.yml
        set +u; conda deactivate ; set -u

        set +u; source activate pipeone_nm_2; set -u
        samtools index ${bam} -b
        CIRIquant --config config.yml -t ${task.cpus } \\
        -1 ${reads[0]} -2 ${reads[1]} -o ${id} \\
        -p ${id} --circ ${ciri} --bam ${bam} --tool CIRI2
       
        """
}


process PrepDE_circRNA_Count_gene_reads {
	
        publishDir "${params.outdir}/ciri/ciriquant/", mode: 'copy',
            saveAs: {filename -> "diff/$filename"}
        
        input:
        path("ciriquant/*")
       
        
        
        
        output:
        path("transcript_count_matrix.csv")
        path("gene_count_matrix.csv")


        script:

        """
        set +u; source activate pipeone_nm; set -u
        find -L ./ciriquant -name "*_out.gtf"|sort |awk '{print \$3, \$0}' FS="/" OFS="\t" > sample_gene.lst
        prepDE.py -i sample_gene.lst -g gene_count_matrix.csv -t transcript_count_matrix.csv

        
        """
}
