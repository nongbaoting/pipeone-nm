

process Fasterq_dump{
    tag {id}

    publishDir "${params.outdir}/fasterq_dump/", mode: 'copy',
        saveAs: {filename -> 
            if(filename =~ /fastq/) "fastq/${filename}"
            else "log/${id}.${filename}" 
            }

    input:
    tuple val(id), path(sra) 

    output:
    tuple val(id), path("${id}_1.fastq"), path("${id}_2.fastq"), emit: reads
    

    script:
    """
    set +u; source activate pipeone_nm; set -u
    fasterq-dump ${sra} -e 8 -o ${id}
    """
		
}