set ns [new Simulator]
set nf [open outrot.nam w]
$ns namtrace-all $nf
set np [open outrot.tr w]
$ns trace-all $np

$ns color 1 Blue
$ns color 2 Green

proc finish {} {
global ns nf np
$ns flush-trace
close $np
close $nf
exec nam outrot.nam $
exit 0}

for {set i 0} {$i < 9 } {incr i} {
set n($i) [$ns node] }

for {set i 0} {$i < 9 } {incr i} {
$ns duplex-link $n($i) $n([expr $i+1]) 2Mb 10 ms DropTail }
$ns duplex-link $n(8) $n(0) 2Mb 10 ms DropTail

set udp [new Agent/UDP]
$ns attach-agent $n(3) $udp
set null [new Agent/Null]
$ns attach-agent $n(8) $null
$ns connect $udp $null

set cbr [new Application/Traffic/CBR]
$cbr attach-agent $udp
$cbr set packetSize_ 300
$cbr set interval_ 0.05

$ns rtproto LS
$ns rtmodel-at 2.5 down $n(0) $n(8)
$ns rtmodel-at 4.5 up $n(0) $n(8)


$ns at 0.5 "$cbr start"
$ns at 5 "$cbr stop"
$ns at 5.1 "finish"
$ns run