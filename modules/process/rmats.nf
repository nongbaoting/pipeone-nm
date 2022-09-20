
process Rmats {
	
        publishDir "${params.outdir}/rmats/", mode: 'link',
            saveAs: {filename -> "$filename"}
        
        input:
        path("assembly.gtf")
        tuple path("AS_con1.txt"), path("AS_con2.txt")
        
        
        
        output:
        
        path("c1c2/")

        script:

        """
        
        python3 /dat1/xush/miniconda3/envs/pipeone_nm/rMATS/rmats.py --readLength 150 --variable-read-length --b1 AS_con1.txt --b2 AS_con2.txt --gtf assembly.gtf -t paired --od c1c2 --tmp tmp

        
        """
}
