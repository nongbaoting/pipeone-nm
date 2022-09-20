#!/usr/bin/perl -w 

use strict;
my($geneName,$genePred,$outfile) = @ARGV;

my @lines=read_lines($geneName);
my %hash;
foreach(@lines){
	next if /^\#/;
	#my ($transcript,$gene_id,$gene_name) = split(/\t/);
	my ($gene_name,$transcript) = split(/\t/);
	$hash{$transcript} = $gene_name;
}

my @gePredLines = read_lines($genePred);
my @table;
foreach(@gePredLines){
	my @xl = split(/\t/);
	if(defined $hash{$xl[0] } ){
		my $line = "$hash{$xl[0]}\t$_";
		push @table,$line;
	}else{print STDERR "miss: $_\n";
	}
}
write_table2file(\@table,$outfile);

sub read_lines{
    my ($file) = @_;
    open my $fh, "<", $file or die "error: can not open file $file $!\n";
    my @lines= grep{chomp;}<$fh>;
    close $fh;
    @lines;
}
sub write_table2file{
    my ($tsv_ref, $fileOut) = @_;
    open my $fh_o, ">", $fileOut or die "error: can not open file $fileOut : $!\n";
    foreach my $lines (@{ $tsv_ref}){
        print $fh_o "$lines\n";
    }
    close $fh_o;
}
