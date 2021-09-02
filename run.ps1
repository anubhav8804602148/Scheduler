Write-Output "Started . . ."
Get-Date
Set-location "d:\my_projects\Scheduler\" 
Remove-item *.log 
Remove-item *.out 
Remove-item *.error
if ($?){
    javac Scheduler.java >compiler.out 2>compiler.error 
}; 
if ($?) { 
    java Scheduler > Scheduler.out 2>Scheduler.error
}
Write-Output "Ending . . ."
Get-Date
