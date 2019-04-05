#coding: utf-8
import requests
import os

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
    if(os.path.exists(Gs) == False):
        os.mkdir(Gs);
    for i in range(0,l):
        qa = cd[i].replace("\n","").split("/");
        cc = qa[len(qa)-1].replace("?","").replace("*","");
        url = requests.get(cd[i].replace("\n",""),allow_redirects=False);
        print(cc);
        with open(Gs+"/"+cc,"wb") as f:
            f.write(url.content);
                
