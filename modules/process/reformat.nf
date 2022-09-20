
process Reformat {
	tag {id}
        publishDir "${params.outdir}/reformat/", mode: 'link',
            saveAs: {filename -> "$filename"}
        
        input:
        tuple val(id), path(reads)
        val(min_length)
        
        
        
        output:
        
        tuple val(id), path("${id}_${min_length}bp_R1.fq.gz"), path("${id}_${min_length}bp_R2.fq.gz"), emit: trimmed_reads
        path("*.fq.gz"), emit: no_id
        
        script:

        """
        set +u; source activate pipeone_nm; set -u
        reformat.sh in1=${id}_1.fastq in2=${id}_2.fastq minlength=${min_length} out1=${id}_${min_length}bp_R1.fq.gz out2=${id}_${min_length}bp_R2.fq.gz

        
        """
}
