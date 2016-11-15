function[b] = Extraction(im, beta)
dwtmode('per');
[c,s] = wavedec2(im,3,'db9');
L3 = s(1);
H = c(1:L3*L3);
b = zeros(size(H));
for i = 1:L3*L3
    if(mod(H(i),beta)>(beta/2))
        b(i) = 1;
    end
end
end