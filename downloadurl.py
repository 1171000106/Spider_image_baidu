#coding: utf-8
import requests

if(__name__ == "__main__"):
    F = open("Need.txt");
    Gs = str(F.read());
    print(Gs);
    F.close();
    F = open(Gs+".txt");
    cd = [];
    for L in F.readlines():
        cd.append(L);
    F.close();
    l = len(cd);
    for i in range(0,l):
        qa = cd[i].replace("\n","").split(".");
        cc = qa[len(qa)-1];
        url = requests.get(cd[i].replace("\n",""));
        print(str(i)+"."+cc);
        with open(str(i)+cc,"wb") as f:
            f.write(url.content);
                
