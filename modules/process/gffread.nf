
process Gffread {
		
        publishDir "${params.outdir}/gffread/", mode: 'link',
            saveAs: {filename -> 
                if (filename =~/fasta/) "exon/${filename}"
                else null
                }
        
        input:
        path("genome.fna")
        path("assembly.gtf")
        
        
        
        output:
        path("Transcripts.fasta") , emit: exon
        
        
        
        script:

        """
        set +u; source activate pipeone_nm; set -u
        gffread -g genome.fna -w Transcripts.fasta -M -K --table @geneid assembly.gtf
        """
}
