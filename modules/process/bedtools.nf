
process Bedtools {
	tag {id}
        publishDir "${params.outdir}/bedtools/", mode: 'link',
            saveAs: {filename -> "$filename"}
        
        input:
        path("genomic.fna")
        tuple val(id), path(ciriquant)
        
        
        
        output:
        
        tuple val(id), path("${id}.fasta"), emit: getfasta

        script:

        """
        set +u; source activate pipeone_nm_3; set -u
        bedtools getfasta -fi genomic.fna -bed ${id}.bed -fo ${id}.fasta
        set +u; source activate pipeone_nm; set -u
        
        """
}
