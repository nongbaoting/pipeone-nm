process Bwaindex {
	
        publishDir "${params.outdir}/bwaindex/", mode: 'link',
            saveAs: {filename -> 
                if (filename =~/fna/) "${filename}"
                else null
                }
        
        input:
        path("genomic.fna")

        output:
        path("genomic.fna*"), emit: bwaindex
        
        script:

        """
        set +u; source activate pipeone_nm; set -u
        bwa index genomic.fna

        """
}

process Bwamem {
        label "bigCPU"
	tag {id}	
        publishDir "${params.outdir}/bwamem/", mode: 'link',
            saveAs: {filename -> 
                if (filename =~/sam/) "${filename}"
                
                else null
                }
        
        input:
        path(index)
        tuple val(id), path(reads)
        
        
        
        output:
        tuple val(id), path("${id}.sam"), emit: bwamem
        
        
        
        
        script:

        """
        set +u; source activate pipeone_nm; set -u
        bwa mem -T 19 -t 10 genomic.fna ${reads[0]} ${reads[1]} > ${id}.sam

        
        """
}