def bin2ascii (binary = '101010'):
    asciistromg = ''
    binvals = []

    nextclump = ''

    for char in binary:
        nextclump+=char
        if len(nextclump) == 8:
            binvals.append(nextclump)
            nextclump = ''
    
    for binval in binvals:
        dec