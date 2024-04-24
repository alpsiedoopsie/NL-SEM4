set ns [new Simulator]



set anim [open out.nam w]

$ns namtrace-all $anim



$ns color 1 Blue

$ns rtproto DV



proc finish {} {

	global ns anim

	$ns flush-trace

	close $anim

	exec nam out.nam &

	exit 0

}



set n0 [$ns node]

set n1 [$ns node]

set n2 [$ns node]

set n3 [$ns node]

set n4 [$ns node]



$ns duplex-link $n0 $n1 2Mb 10ms DropTail

$ns duplex-link $n0 $n3 2Mb 10ms DropTail

$ns duplex-link $n1 $n2 2Mb 10ms DropTail

$ns duplex-link $n3 $n4 2Mb 10ms DropTail

$ns duplex-link $n2 $n4 2Mb 10ms DropTail



$ns duplex-link-op $n0 $n1 orient down-left

$ns duplex-link-op $n0 $n3 orient down-right

$ns duplex-link-op $n1 $n2 orient down

$ns duplex-link-op $n3 $n4 orient down-left

$ns duplex-link-op $n2 $n4 orient down-right



set tcp [new Agent/TCP]

$ns attach-agent $n0 $tcp

set sink [new Agent/TCPSink]

$ns attach-agent $n4 $sink

$ns connect $tcp $sink

$tcp set fid_ 1



set ftp [new Application/FTP]

$ftp attach-agent $tcp

$ftp set type_ FTP

$ftp set packet_size_ 1000

$ftp set rate_ 1mb



$ns at 0.1 "$ftp start"

$ns rtmodel-at 1.0 down $n3 $n4

$ns rtmodel-at 2.0 up $n3 $n4

$ns at 3.0 "$ftp stop"

$ns at 3.1 "finish"



$ns run



