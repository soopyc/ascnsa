.data
input_s: .asciiz "Input an integer: "
add_r_s: .asciiz "The result of the addition is: "
sub_r_s: .asciiz "The result of the subtration is: "

.text
main:
	# first int
	li $v0, 4 # syscall print_string
	la $a0, input_s
	syscall
	li $v0, 5 # syscall read_int		
	syscall
	move $s0, $v0
	
	# second int
	li $v0, 4 # syscall print_string
	la $a0, input_s
	syscall
	li $v0, 5 # syscall read_int
	syscall
	move $s1, $v0
	
	# perform addition
	add $t0, $s0, $s1
	li $v0, 4 # syscall print_str
	la $a0, add_r_s
	syscall
	li $v0, 1 # syscall print_int
	move $a0, $t0
	syscall
	li $v0, 11
	li $a0, 10
	syscall

	# perform subtraction
	sub $t0, $s0, $s1
	li $v0, 4
	la $a0, sub_r_s
	syscall
	li $v0, 1
	move $a0, $t0
	syscall
	
	# end
	li $v0, 10
	syscall
