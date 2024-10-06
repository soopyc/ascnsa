.data
result_msg: .asciiz "The result is: "

.text
main:
	# Initialize values
	addi $s0, $zero, 10 # $s0 = 10
	addi $s1, $zero, 5 # $s1 = 5
	li	$s2, 7 # $s2 = 7
	li	$s3, 3 # $s3 = 3
	
	# Perform addition
	add $t0, $s0, $s1 # $t0 = $s0 + $s1
	add $t1, $s2, $s3 # $t1 = $s2 + $s3
	
	# Perform subtraction
	sub $t2, $t0, $t1 # $t2 = $t0 - $t1 = ($s0 + $s1) - ($s2 + $s3)
	
	# Print result of addition
	li $v0, 4 # syscall code for print_string
	la $a0, result_msg
	syscall

	li $v0, 1 # syscall code for print_integer
	move $a0, $t2
	syscall
	
	# Print newline
	li $v0, 11 # syscall code for print_character
	li $a0, 10 # ASCII code for newline
	syscall
	
	# Exit program
	li $v0, 10
	syscall