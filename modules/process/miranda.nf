
process Miranda {
	tag {id}
        publishDir "${params.outdir}/miranda/", mode: "copy",
            saveAs: {filename -> "$filename"}
        
        input:
        tuple val(id), path("${id}.fasta")
        path(fish_miRNA)
        
        
        output:
        
        path("${id}_circ_miRNA.txt")

        script:

        """
        set +u; source activate pipeone_nm; set -u
        miranda ${fish_miRNA} ${id}.fasta > ${id}_circ_miRNA.txt

        
        """
}
