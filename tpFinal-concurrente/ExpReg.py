import re


def removeInvariant (m):
    global log
    i = 1
    j = 0
    while(i <= len(m.groups())):
        span = m.span(i)
        log = log [:(span[0] - j)] + log[(span[1] - j):]
        j = j + span[1] - span[0]
        i = i + 2

file = open("log.txt","r")
log = file.read()

mm = re.search(r"\-",log)
log = log [:mm.start()]

print (log)
listaMcant = [0,0,0,0,0,0,0,0]
while(len(log) > 0):
    m0 = re.search(r"(T0)(.*?)(T1(?=T))(.*?)(T3)(.*?)(T5)(.*?)(T9)(.*?)(T15)", log)
    m1 = re.search(r"(T0)(.*?)(T1(?=T))(.*?)(T3)(.*?)(T5)(.*?)(T10)(.*?)(T16)", log)
    m2 = re.search(r"(T0)(.*?)(T1(?=T))(.*?)(T3)(.*?)(T13)(.*?)(T7)(.*?)(T9)(.*?)(T15)", log)
    m3 = re.search(r"(T0)(.*?)(T1(?=T))(.*?)(T3)(.*?)(T13)(.*?)(T7)(.*?)(T10)(.*?)(T16)", log)
    m4 = re.search(r"(T0)(.*?)(T2)(.*?)(T4)(.*?)(T6)(.*?)(T11)(.*?)(T15)", log)
    m5 = re.search(r"(T0)(.*?)(T2)(.*?)(T4)(.*?)(T6)(.*?)(T12)(.*?)(T16)", log)
    m6 = re.search(r"(T0)(.*?)(T2)(.*?)(T4)(.*?)(T14)(.*?)(T8)(.*?)(T11)(.*?)(T15)", log)
    m7 = re.search(r"(T0)(.*?)(T2)(.*?)(T4)(.*?)(T14)(.*?)(T8)(.*?)(T12)(.*?)(T16)", log)
    flag = True
    listaM = [m0,m1,m2,m3,m4,m5,m6,m7]
    for mi in listaM:
        if (flag and mi):
            minim = mi
            flag = False
        if(mi):
            if (mi.end(0) < minim.end(0) ):
                minim = mi
    if(flag):
        break
    print("El invariante a borrar es: m" + str(listaM.index(minim)) +'\n')
    listaMcant[listaM.index(minim)] += 1
    removeInvariant(minim)
    print(log)
print("\nTU SALDO ES: \n" + log)
print(listaMcant)
print("NOS SOBRARON UNA BOCHJA DE T0: " + str(len(re.findall("T0", log))))
