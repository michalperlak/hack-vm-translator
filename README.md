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