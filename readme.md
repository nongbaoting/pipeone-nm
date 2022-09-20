#### Prerequisites

1. [Docker](https://www.docker.com/) or [conda](https://docs.conda.io/en/latest/miniconda.html)
2. Java (version >= 1.7)
3. [Nextflow](https://www.nextflow.io/) (version >= 20.07.1.5413)



#### Installation

__1. Download PipeOne-NM__

```
git clone https://github.com/nongbaoting/PipeOne.git
```



__2. Pull  docker images__

Pull down the PipeOne-NM Docker image

```bash
docker pull nongbaoting/pipeone:latest
```



__3. Configuration__

Modify the program configuration file `PipeOne/conf/genomes.config`,  change the line below:

params {

  genomes {


  "fish_test" {

    refgenome = "/path/to/genome/genomic.fna"

    fish_miRNA = '/path/to/genome/public5/l/FishmiRNA-June2021-dre-mature.fasta' // for miranda
     	uniprot = "/path/to/genome/uniprot_sprot.pep"                                                     // for Trinotate
     	 pfam = "/path/to/genome/Pfam-A.hmm"                                                                 // for Trinotate
    	 sqlite = "/path/to/genome/Trinotate_20210616.sqlite"                                          // forTrinotate trinotat
     	 min_length = 150
    }

  }

}


__4. Run the Pipeline__

nextflow run /path/to/pipeOne-nm  -profile docker -resume --sra './sra/*.sra'
