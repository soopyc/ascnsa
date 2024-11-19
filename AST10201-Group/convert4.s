# ----------------------------------
# Data Segment
.data
msg:	.asciiz	" Info: this program is to convert a number from any base (between 2 and 16) to binary, octal, decimal and hexadecimal number\n"

promptBase:	.asciiz "\n Enter any base between 2 and 16: "
promptNumb:	.asciiz "\n Enter a number: "

msgWrong:	.asciiz "\n Each digit should be between 0 and "

ansBin:	.asciiz "\tConvert it to Bin: "
ansDec:	.asciiz "\tConvert it to Dec: "
ansOct:	.asciiz "\tConvert it to Oct: "
ansHex:	.asciiz "\tConvert it to Hex: "
comp:	.asciiz "\n Do you wanna continue (y/n)? "
newLine:	.asciiz	"\n"
end:	.asciiz "\n End\n"
buffer:	.space	32
bin_buffer: .space 32
octBuffer: .space 32
hex_buffer: .space 32
hex_digits: .asciiz "0123456789ABCDEF"

msg_invalid_base:	.asciiz "   The base must between 2 and 16! Please enter again!"
msg_illegal_char:	.asciiz "	One of the characters is illegal for the base."

# ----------------------------------
# Text/Code Segment

.text
.globl main
main:
	la	$a0, msg
	li	$v0, 4
	syscall

prompt_base:
	# prompt to user to input base between 2 and 16
	la	$a0, promptBase
	li	$v0, 4
	syscall

	# read integer
	li $v0, 5
	syscall

	# make $s0 = base
	move $s0, $v0

	# check if base is valid
	bgt $s0, 16, outofRange_base
	blt $s0, 2, outofRange_base

	# if base is valid, then continue the code
	j print_enterNo

outofRange_base:
	la $a0, msg_invalid_base	# prompt user input again
	li $v0, 4
	syscall

	j prompt_base	# prompt user input again

print_enterNo:

	# TODO
	la	$a0, promptNumb
	li	$v0, 4
	syscall

	# read string
	li $v0, 8
	la $a0, buffer
	li $a1, 32
	syscall
	
	# call str2int
	la	$a0, buffer
	move	$a1, $s0
	jal	str2int
	
	# check if $v0 is 0 (incorrect) or 1 (correct)
	
	# print result to check $v1 value (for checking only)
	#li $v0, 1
	#move $a0, $v1
	#syscall

	# copy $v1 to $t0
	move $t0, $v1 # used in print8 function
	# copy $v1 to $t1
	move $s1, $v1 # used in print10 function

	
	# jump print2 
	jal print2
	# after print2 -> print8 -> print10 -> print16 -> continue
	
	
# this step prompts the user if they want to restart the entire thing.
continue:
	la	$a0, comp	# show string repeat
	li	$v0, 4
	syscall

	li	$v0, 12		# read char
	syscall

	bne	$v0, 'y', noty	 # 'y'
	j	prompt_base

noty:
	la	$a0, end
	li	$v0, 4
	syscall
	li	$v0, 10
	syscall

.end main

# ----------
# procedure: str2int
# desc: parse string to integer
# input: $a0 = starting address of buffer
# input: $a1 = base
#  temp: $t0 = character to be checked and added to $v1.
#  temp: $t1 = the next character after $t0.
#  temp: $t2 = sometimes mutated main current index 
#  temp: $t3 = often-mutated current index for adding to the value in the addBaseLoop label.
#  temp: $t6 = count string length: address of buffer
#  temp: $t7 = count string length: temporary storage of current character.
# return value = $v1
# return flag $v0: 0-wrong, 1-correct
# -----------
.globl str2int
str2int:
	# count string length
	# i would use the stack to store '$ra's and registers but that's way too complicated to write by hand.
	move	$t6, $a0	# set $t6 to starting address of buffer.
	# initialize $v0 and $v1.
	move	$v0, $zero
	move	$v1, $zero

countStringLoop:
	lb	$t7, ($t6) # load char into $t7
	beq	$t7, '\0', countStringLoopEnd # end if char is NUL
	beq	$t7, '\n', countStringLoopEnd # end if char is LF
	addiu	$t6, $t6, 1 # shift address by 1 byte.
	j countStringLoop
countStringLoopEnd:
	# the length is the ending address minus the starting address.
	sub	$t2, $t6, $a0
	sub	$t2, $t2, 1 # subtract one so we get 0 at the ones place.

str2intLoop:
	lb	$t0, 0($a0) # load the next char into $t0
	lb	$t1, 1($a0) # load the next-next char into $t1
	bgt	$t0, '9', handleAlpha # if loaded char is greater than 9's ascii codepoint, it's an alphabet character.
	sub	$t0, $t0, '0' # effectively turn ascii "0" into 0.
	b	checkAndAdd
handleAlpha:
	sub $t0, $t0, 55 # ('F'-15 = 55) effectively turn ascii "F" into 15
	# b	checkAndAdd	# nop, for consistency

# check the validity, and if so add the value to $v1.
checkAndAdd:
	blt	$t0, 0, checkFailure	# all values below 0 is invalid.
	bge	$t0, 16, checkFailure	# all values above 15 is invalid.
	bge	$t0, $a1, checkFailure	# all values above the base is invalid.
	move	$t3, $t2	# set $t3 to current index.

addBaseLoop:
	beqz	$t3, addBaseLoopEnd
	# if we passed those checks, we can add it to the value.
	mul	$t0, $t0, $a1	# multiply the value by the base by (index) times.
	subi	$t3, $t3, 1
	j	addBaseLoop

addBaseLoopEnd:
	add	$v1, $v1, $t0 # finally add the value to $v1.
	j	endOrBump

checkFailure:
	li	$v0, 4
	la	$a0, msg_illegal_char
	syscall # print out an error message.
	jr	$ra


# check if the next char is \n or NUL. if so, return. if not, add 4 to $t0.
endOrBump:
	beq	$t1, '\n', endStr2int
	beq	$t1, '\0', endStr2int
	
	addiu	$a0, $a0, 1	# bump $a0 by 1
	subi	$t2, $t2, 1	# subtract index by 1
	j	str2intLoop

endStr2int:
	li	$v0, 1	# if it reached this far it should be correct.
	jr	$ra

.end str2int

# ----------
# procedure: print2
# desc: decimal to binary
# $a2 = the integer to print
# return value: $v1 = $a2
# -----------
.globl print2
print2:
# syscall for new line
	li $v0, 11
    	li $a0, 10
    	syscall
    	
	li	$t1,0		#$t1 = index
	la	$t2,bin_buffer	#$t2 = buffer	
	
	
	move 	$s0,$t0 # move $t0 to $s0
	
		
convert_loop:	
	# if $t0 =0 , jump to print_loop
	beqz	$t0,print_msg
	li	$t3,2		#$t3=2
	div	$t0,$t0,$t3	#$t0=$t0/$t3
	mfhi	$t4		#$t4 = remaindar
	sb	$t4,0($t2)	#store remaindar to buffer
	addi	$t2,$t2,1	#move to next	
	addi	$t1,$t1,1	#index +1
	j	convert_loop
	

print_msg:	
# print ansBin message
	la	$a0, ansBin
	li	$v0, 4
	syscall
	
	
print_loop:
	# print inverse	
	# if $t1 =0 ,jump to print 8
	beqz	$t1,jump
	lb	$a0,-1($t2)	#load byte from buffer 
	addi	$a0,$a0,48	#change to '0' and '1'	from decimal to char by add 48
	li	$v0,11		#syscall print char
	syscall
	subi	$t2,$t2,1	#move to next 
	subi	$t1,$t1,1	#index -1
	j	print_loop
	


	
    	
 jump:   	
    	# jump print8 function
	j print8

.end print2


# -----------
# procedure: print8
# desc: decimal to octal
# -----------
.globl print8
print8:
	la $t3, octBuffer # load address of octBuffer
	
	# syscall for new line
	li $v0, 11
    	li $a0, 10
    	syscall
    	
    	move $t0, $s0 # move $s0 to $t0
dec2oct_convert:
	beqz $t0, printOct_Result
	
	div $t1, $t0, 8	 	# $t0 divide by 8 and store result in $t1
	mfhi $t2			# move the remainder to $t2
	addi $t2	, $t2, '0' 	# convert digit to ascii character
	sb   $t2, 0($t3)   	# store ascii character in buffer
	addi $t3, $t3, 1        	# add 1 in buffer to read next character
	move $t0, $t1          	# update decimal number with quotient
	j dec2oct_convert
	
printOct_Result:
	li $v0, 4                 # syscall for print string
   	la $a0, ansOct            # load address of result message into $a0
    	syscall                   # make the syscall
    	
    	# Print the octal number in reverse order
    	li $v0, 4                 # syscall for print string
    	sub $t3, $t3, 1          # last stored character
	
reverse_loop:
    	lb $a0, 0($t3)            # load byte from buffer into $a0
    	beqz $a0, print10      	 # if null, then jump to print10
    	
    	 # print character
    	li $v0, 11               
    	syscall               
    	 # move to previous character
    	sub $t3, $t3, 1       
    	# jump reverse_loop
    	j reverse_loop             
	

.end print8

# ----------
# procedure: print10
# desc: print decimal value
# -----------
.globl print10
print10:

	# syscall print new line
	li $v0, 11
    	li $a0, 10
    	syscall
    	
    	# print ansDec message
	li $v0, 4                
   	la $a0, ansDec            
    	syscall    
    	
    	li $v0, 1            # syscall for print string
   	move $a0, $s1          # load address of result message into $a0
    	syscall   
    	
     	j print16
	

.end print10



# ----------
# procedure: print16
# desc: decimal to hexadecimal
# $a2 = the integer to print
# return value: $v1 = $a2
# -----------
.globl print16
print16:

	# syscall print new line
	li $v0, 11
    	li $a0, 10
    	syscall
    	
    	#load hex buffer
    	la $t5, hex_buffer
	la $t6, hex_buffer
	addi $t5, $t5, 30
	
	move $t0, $s1 #copy the integer to t0 register
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
	
	beqz $t1, print16e #if quotient reaches 0 (meaning end of decimal), go to print the result
	b loop

print16e: 
	lb $t7, ($t6) #load value of $t6 address into $t7
	beqz $t7, checkifzero #if $t7 is zero
	
	# print ansDec message
	li $v0, 4                
   	la $a0, ansHex           
    	syscall    
    	
	li $v0, 4 #print value from register $t6 position
	la $a0, ($t6)
	syscall
	
	j continue
	
checkifzero: 
	addi $t6, $t6, 1 #add 1 to address of $t6, skipping the leading zero
	j print16e
    	


.end print16
