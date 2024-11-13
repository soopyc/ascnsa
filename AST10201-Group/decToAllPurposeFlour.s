# ----------------------------------
# Data Segment
.data
dec_prompt: .asciiz "Enter a binary number: "
hex_prompt: .asciiz "The hex representation is: "
hex_buffer: .space 32
hex_digits: .asciiz "0123456789ABCDEF"
itsazero: .asciiz "ZERO!"

# ----------------------------------
# Text/Code Segment

.text
.globl	main
main:	
	la $t5, hex_buffer
	la $t6, hex_buffer
	addi $t5, $t5, 30
	#print "Enter a binary number:" 
	li $v0, 4
	la $a0, dec_prompt
	syscall
	
	#read user input and store to $v0
	li $v0, 5
	syscall
	
	move $t0, $v0 #copy the integer to t0 register
	move $t1, $t0
	
loop :
	div $t1, $t1, 16 #divide user input number by 16
	mfhi $t2 #pull remainder into t2
	mflo $t1 #set t1 as quotient, replacing the old one
	
	la $t4, hex_digits #load initial address of ascii string to $t4 
	add $t4, $t4, $t2 #increment address by remainder from 'mfhi' 
	lb $t2, ($t4) #load the character from $t4 address into $t2 
	sb $t2, ($t5) #store into buffer
	addi $t5, $t5, -1 #decrement index
	
	beqz $t1, print #if quotient reaches 0 (meaning end of decimal), go to print the result
	b loop

print: 
	lb $t7, ($t6) #load value of $t6 address into $t7
	beqz $t7, checkifzero #if $t7 is zero
	
	li $v0, 4 #print value from register $t6 position
	la $a0, ($t6)
	syscall
	
	li	$v0,10	# call code for exit
	syscall		# system call
	
checkifzero: 
	addi $t6, $t6, 1 #add 1 to address of $t6, skipping the leading zero
	j print

	
	
.end main


