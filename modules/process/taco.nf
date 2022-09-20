
process Taco {
		
        publishDir "${params.outdir}/TACO/", mode: 'link',
            saveAs: {filename -> 
                if (filename =~/log/) "logs/${filename}"
                else if(filename =~ /gtf/) "$filename"
                else null
                }
        
        input:
        path("gtf_Path/*")
        
        
        
        output:
        path("TACO/assembly.gtf") , emit: TACO
        path("TACO.log"), emit: log
        
        
        script:

        """
        set +u; source activate pipeone_nm_2; set -u
        ls ./gtf_Path | awk '{print "./gtf_Path/"\$0}' > GTFs
        taco_run -p 10 -o TACO GTFs > TACO.log

        
        """
}
