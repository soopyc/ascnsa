.data
numbers: .word 10, 20, 30, 40, 50
size: .word 5
result: .asciiz "The sum of the array elements is: "

.text
.globl main
main:
	la $t0, numbers # Load the base address of the array into $t0
	lw $t1, size # Load the size of the array into $t1
	li $t2, 0 # Initialize the sum to 0
loop:
	# FILL IN THE CODE: Load the current element from the array into $t3
	lw $t3, ($t0)
	# FILL IN THE CODE: Add the current element to the sum in $t2
	add $t2, $t2, $t3

	addi $t0, $t0, 4 # Move to the next element in the array
	addi $t1, $t1, -1 # Decrement the loop counter
	bgtz $t1, loop # Continue the loop if there are more elements
	
	# Print the result
	li $v0, 4
	la $a0, result
	syscall
	
	# Print the sum stored in $t2
	li $v0, 1
	move $a0, $t2
	syscall
	
	# Exit the program
	li $v0, 10
	syscall