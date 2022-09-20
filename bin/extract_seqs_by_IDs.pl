#!/usr/bin/perl

use strict;
use warnings;
use Bio::SeqIO;
use Cwd;
use Getopt::Long;
my ($fasta,$info,$outfile, $col_info);
$col_info=0;

my $usage = <<__EOF__;

Usage:
	$0 -f <fasta file> -i <id info> -o <out file>
	Options:
		-f <file>	input fasta file
		-i <string>	id info file
		-o <file>	output fasta file 
		-c <int>	the number of column defaults 0
__EOF__


;


GetOptions(
			"f=s"	=> \$fasta,
			"i=s"	=> \$info,
			"o=s"	=> \$outfile,
			"c=i"	=> \$col_info,
			)or die;
die $usage unless ($fasta && $info && $outfile);
my %ids;
my $in=Bio::SeqIO->new(-file=>"$fasta",
						-format=>"fasta"
						);
my $out=Bio::SeqIO->new(-file=>">$outfile",
						-format=>"fasta"
						);
open IN,"<", "$info";
while(<IN>){
	chomp;
	my @items=split(/\s+/,$_);
	$ids{$items[$col_info]}=1;
	}
while(my $seq_ojb=$in->next_seq){
		my $id=$seq_ojb->id;
				if(defined $ids{$id}){
					$out->write_seq($seq_ojb); 			
					}
		}

