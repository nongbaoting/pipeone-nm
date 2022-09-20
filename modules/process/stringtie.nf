
process Stringtie {
		
        tag {id}
        publishDir "${params.outdir}/stringtie/", mode: 'link',
            saveAs: {filename -> 
                if(filename =~ /gtf/ )  "gtf/$filename"
                else null
                }
        
        input:
        tuple val(id), path(bam)
        
        
        output:
        path("${id}.gtf") , emit: gtf

        
        
        script:

        """
        set +u; source activate pipeone_nm; set -u
        stringtie $bam -o ${id}.gtf
        """
       
 
}