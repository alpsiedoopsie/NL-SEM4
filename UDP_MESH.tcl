set ns [new Simulator]



set nf [ open out.nam w]

$ns namtrace-all $nf



$ns color 1 Blue 

$ns color 2 red

$ns color 3 green 

$ns color 4 yellow

$ns color 5 black

$ns color 6 pink



proc finish {} {

global ns nf

$ns flush-trace 

close $nf

exec nam out.nam &

exit 0 

}

set n0 [$ns node]

set n1 [$ns node]

set n2 [$ns node]

set n3 [$ns node]



$ns duplex-link $n0 $n1 10Mb 10ms DropTail

$ns duplex-link $n0 $n3 10Mb 10ms DropTail

$ns duplex-link $n0 $n2 10Mb 10ms DropTail

$ns duplex-link $n2 $n1 10Mb 10ms DropTail

$ns duplex-link $n3 $n2 10Mb 10ms DropTail

$ns duplex-link $n1 $n3 10Mb 10ms DropTail



$ns duplex-link-op $n0 $n1 orient right

$ns duplex-link-op $n0 $n3 orient right-down

$ns duplex-link-op $n0 $n2 orient down

$ns duplex-link-op $n2 $n1 orient right-up

$ns duplex-link-op $n3 $n2 orient left

$ns duplex-link-op $n1 $n3 orient down



set udp0 [new Agent/UDP]

$ns attach-agent $n0 $udp0

set null0 [new Agent/Null]

$ns attach-agent $n1 $null0

$ns connect $udp0 $null0

$udp0 set fid_ 1



set udp1 [new Agent/UDP]

$ns attach-agent $n0 $udp1

set null1 [new Agent/Null]

$ns attach-agent $n3 $null1

$ns connect $udp1 $null1

$udp1 set fid_ 2



set udp2 [new Agent/UDP]

$ns attach-agent $n0 $udp2

set null2 [new Agent/Null]

$ns attach-agent $n2 $null2

$ns connect $udp2 $null2

$udp2 set fid_ 3



set udp3 [new Agent/UDP]

$ns attach-agent $n2 $udp3

set null3 [new Agent/Null]

$ns attach-agent $n1 $null3

$ns connect $udp3 $null3

$udp3 set fid_ 4



set udp4 [new Agent/UDP]

$ns attach-agent $n3 $udp4

set null4 [new Agent/Null]

$ns attach-agent $n2 $null4

$ns connect $udp4 $null4

$udp4 set fid_ 5



set udp5 [new Agent/UDP]

$ns attach-agent $n1 $udp5

set null5 [new Agent/Null]

$ns attach-agent $n3 $null5

$ns connect $udp5 $null5

$udp5 set fid_ 6



set cbr0 [new Application/Traffic/CBR]

$cbr0 set type_ CBR

$cbr0 set packet_size_ 1000

$cbr0 set rate_ 1mb

$cbr0 attach-agent $udp0



set cbr1 [new Application/Traffic/CBR]

$cbr1 set type_ CBR

$cbr1 set packet_size_ 1000

$cbr1 set rate_ 1mb

$cbr1 attach-agent $udp1



set cbr2 [new Application/Traffic/CBR]

$cbr2 set type_ CBR

$cbr2 set packet_size_ 1000

$cbr2 set rate_ 1mb

$cbr2 attach-agent $udp2



set cbr3 [new Application/Traffic/CBR]

$cbr3 set type_ CBR

$cbr3 set packet_size_ 1000

$cbr3 set rate_ 1mb

$cbr3 attach-agent $udp3



set cbr4 [new Application/Traffic/CBR]

$cbr4 set type_ CBR

$cbr4 set packet_size_ 1000

$cbr4 set rate_ 1mb

$cbr4 attach-agent $udp4



set cbr5 [new Application/Traffic/CBR]

$cbr5 set type_ CBR

$cbr5 set packet_size_ 1000

$cbr5 set rate_ 1mb

$cbr5 attach-agent $udp5





$ns at 1.0 "$cbr0 start"

$ns at 1.0 "$cbr1 start"

$ns at 1.0 "$cbr2 start"

$ns at 1.0 "$cbr3 start"

$ns at 1.0 "$cbr4 start"

$ns at 1.0 "$cbr5 start"

$ns at 4.0 "$cbr1 stop"

$ns at 4.0 "$cbr2 stop"

$ns at 4.0 "$cbr3 stop"

$ns at 4.0 "$cbr4 stop"

$ns at 4.0 "$cbr5 stop"

$ns at 4.0 "$cbr0 stop"

$ns at 5.0 "finish"

$ns run





