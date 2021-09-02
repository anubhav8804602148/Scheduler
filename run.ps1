Write-Output "Started . . ."
Get-Date
Set-location "D:\my_projects\Scheduler\Scheduler" 
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
