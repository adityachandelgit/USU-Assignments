function[opIm, b] = Embedding(im, beta)
dwtmode('per');
[c,s] = wavedec2(im, 3, 'db9');
L3 = s(1);
rng default;
b = round(rand(L3, L3));
H = c(1:L3*L3);
for i = 1:L3*L3
    if(b(i)==1)
        if(mod(H(i), beta)>=0.25*beta)
            c(i) = H(i)-mod(H(i),beta)+(0.75*beta);
        else
            c(i) = (H(i)-(0.25*beta))-(mod((H(i)-0.25*beta),beta))+(0.75 * beta);
        end
    else
        if(mod(H(i),beta)<=0.75*beta)
            c(i) = H(i)-(mod(H(i),beta))+(0.25*beta);
        else
            c(i) = (H(i)+(0.5*beta))-(mod((H(i)-0.5 * beta),beta))+(0.25*beta);
        end
    end
end
opIm = uint8(waverec2(c, s, 'db9'));
end