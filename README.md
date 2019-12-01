# hack-vm-translator

## VM Language

### Arithmetic / Logical commands
 - add
 - sub
 - neg
 - eq
 - get
 - lt
 - and
 - or
 - not
 
### Memory access commands
 - pop *segment* i
 - push *segment* i
 
### Branching commands
 - label *label*
 - goto *label*
 - if-goto *label*
 
### Function commands
 - function *functionName* *nVars*
 - call *functionName* *nArgs*
 - return 
 
## Memory segments

 - local
 - argument
 - this
 - that
 - constant
 - static
 - temp
 - pointer

### Local segment

High level operations on *local* variables are translated into VM operations
on the entries of the segment *local*

### Arguments segment

High level operations on *argument* variables are translated into VM operations
on the entries of the segment *argument*

### This segment

High level operations on the *field* variables of the current object are translated
into VM operations on the entries of the segment *this*

### That segment

High level operations on *array entries* are translated into VM operations on the
entries of the segment *that*
 
### Constant segment 

High level operations on the *constant i* are translated into VM operations on the 
segment entry *constant i*
 
### Static segment

High level operations on *static* variables are translated into VM operations
on entries of the segment *static* 
 
### Temp segment

A memory segment for compiler working variables. 
 
### Pointer segment

A memory segment for storing the base addresses of the segments *this* and *that*
 
#### push/pop local/argument/this/that 
   `push segment i` -> `addr = segmentPointer + i, *SP = *addr, SP++`
   
   `pop segment i` -> `addr = segmentPointer + i, SP--, *addr = *SP`
   
#### push constant

   `push constant i` -> `*SP = i, SP++`
   
#### push/pop temp 

   Temp - a fixed 8-place memory segment stored in RAM locations 5 to 12

   `push temp i` -> `addr = 5 + i, *SP = *addr, SP++`
   
   `pop temp i` -> `addr = 5 + i, SP--, *addr = *SP`    
    
#### push/pop pointer 0/1
   Pointer - a fixed 2-place segment
      - accessing pointer 0 should result in accessing `THIS`, 1 should result in accessing `THAT`

   `push pointer 0/1` -> `*SP = THIS/THAT, SP++`   
   
   `pop pointer 0/1` -> `*SP--, THIS/THAT = *SP`