%%%%%%%%%%%%%%%%%%%% ----- Problem 1 I ------ %%%%%%%%%%%%%%%%%%%%%%
close all;

sample = imread('Sample.jpg');
imffshSample = fftshift(fft2(sample));
H = size(sample,1);
W = size(sample,2);
devX = 50;
devY = 25;

GLPFilter = zeros(H,W);
midX = floor(H/2+1);
midY = floor(W/2+1);
for x=1:H
    for y=1:W
        GLPFilter(x,y) = exp(-(((x-midX)*(x-midX))/(2*devX*devX))-(((y-midY)*(y-midY))/(2*25*25)));
    end
end
filteredShifted = uint8(ifft2(ifftshift(imffshSample.*GLPFilter)));
figure,
subplot(1,3,1), imshow(sample), title('Original Image');
subplot(1,3,2), imshow(GLPFilter), title('Gaussian Low-Pass Filter');
subplot(1,3,3), imshow(filteredShifted), title('Filtered Image');
pause;


%%%%%%%%%%%%%%%%%%%% ----- Problem 1 II ------ %%%%%%%%%%%%%%%%%%%%%%

Freq = 50;
Order = 2;
BHPFilter = zeros(H,W); 
midX1 = floor(H/2+1); 
midY1 = floor(W/2+1);
for p=1:H 
    for q=1:W 
        BHPFilter(p,q)= 1/(1+(Freq/(((p-midX1)*(p-midX1)+(q-midY1)*(q-midY1))^0.5))^(2*Order));
    end
end
filteredShifted = ifft2(ifftshift(imffshSample.*BHPFilter));  
figure, 
subplot(1,3,1), imshow(sample), title('Original Image'); 
subplot(1,3,2), imshow(BHPFilter), title('Butterworth High-Pass Filter '); 
subplot(1,3,3), imshow(filteredShifted), title('Filtered Image');
pause;


%%%%%%%%%%%%%%%%%%%% ----- Problem 2 I ------ %%%%%%%%%%%%%%%%%%%%%%%

city = imread('City.jpg');
imffshCity = fftshift(fft2(city));
[H,W] = size(city);
NMFilter = zeros(H,W); 
midX2= floor(H/2+1); 
midY2= floor(W/2+1);
for i= 1:H 
    for j=1:W 
        NMFilter(i,j)= exp(-0.025*((i- midX2)*(i-midX2)+(j-midY2)*(j-midY2))^0.5);
    end
end
filteredCity = uint8(ifft2(ifftshift(imffshCity.*NMFilter))); 
figure,
subplot(1,2,1), imshow(NMFilter), title('Filter'); 
subplot(1,2,2), imshow(filteredCity), title('Turbulent Image');
imwrite(filteredCity, 'BlurCity.bmp');
pause;


%%%%%%%%%%%%%%%%%%%% ----- Problem 2 II ------ %%%%%%%%%%%%%%%%%%%%%%

fftsFilteredCity = fftshift(fft2(imread('BlurCity.bmp')));
WienerFiltered = (((abs(NMFilter).*abs(NMFilter)).*fftsFilteredCity)./ ((NMFilter.*((abs(NMFilter).*abs(NMFilter)))+ 0.0000005)));
WienerFilteredInversed = uint8(ifft2(ifftshift(WienerFiltered)));
figure, imshow(WienerFilteredInversed), title('Restored Image Using Wiener Filter'); 
pause;


%%%%%%%%%%%%%%%%%%%% ----- Problem 3 I ------ %%%%%%%%%%%%%%%%%%%%%%%

imCapitol = imread('Capitol.jpg');
imffshCapitol = fftshift(fft2(imCapitol));
absSample = abs(imffshSample); 
absCapitol = abs(imffshCapitol); 
phaseSample = angle(imffshSample); 
phaseCapitol = angle(imffshCapitol); 
magnitudeSample = uint8(15*log(1+absSample));
magnitudeCapitol = uint8(15*log(1+absCapitol));
figure,
subplot(2,2,1), imshow(magnitudeSample), title('Magnitude Sample'); 
subplot(2,2,2), imshow(phaseSample,[]), title('Phase Sample'); 
subplot(2,2,3), imshow(magnitudeCapitol), title('Magnitude Capitol'); 
subplot(2,2,4), imshow(phaseCapitol,[]), title('Phase Capitol');
pause;


%%%%%%%%%%%%%%%%%%%%% ----- Problem 3 II ------ %%%%%%%%%%%%%%%%%%%%%

changedCapitol = absSample.*((cos(phaseCapitol)+ 1i*sin(phaseCapitol)));
invChangedCapitol= ifftshift(changedCapitol); 
invChangedFFTCapitol= uint8(ifft2(invChangedCapitol,'symmetric'));
ChangedSample =  absCapitol.*((cos(phaseSample)+ 1i*sin(phaseSample)));
invChangedSample = ifftshift(ChangedSample); 
invChangedFFTSample = uint8(ifft2(invChangedSample,'symmetric'));
figure,
subplot(1,2,1), imshow(invChangedFFTCapitol), title('Reconstructed Capitol'); 
subplot(1,2,2), imshow(invChangedFFTSample), title('Reconstructed Sample');
pause;


%%%%%%%%%%%%%%%%%%%%%% ----- Problem 4 ------ %%%%%%%%%%%%%%%%%%%%%%%

boyNoisy = imread('boy_noisy.gif');
BF = fftshift(fft2(boyNoisy));

disp('The values of i and j below are the location of 4 largest pixels in the image. I have found this manually.')

i = 273;
j = 225; 
BF(i,j) = ((BF(i-1,j-1))+(BF(i-1,j))+(BF(i-1,j+1))+(BF(i,j-1))+(BF(i,j+1))+(BF(i+1,j-1))+(BF(i+1,j))+(BF(i+1,j+1)))/8;

i = 257;
j = 256;
BF(i,j) = ((BF(i-1,j-1))+(BF(i-1,j))+(BF(i-1,j+1))+(BF(i,j-1))+(BF(i,j+1))+(BF(i+1,j-1))+(BF(i+1,j))+(BF(i+1,j+1)))/8;

i = 257; 
j = 255; 
BF(i,j) = ((BF(i-1,j-1))+(BF(i-1,j))+(BF(i-1,j+1))+(BF(i,j-1))+(BF(i,j+1))+(BF(i+1,j-1))+(BF(i+1,j))+(BF(i+1,j+1)))/8;

i = 257;
j = 258 ;
BF(i,j) = ((BF(i-1,j-1))+(BF(i-1,j))+(BF(i-1,j+1))+(BF(i,j-1))+(BF(i,j+1))+(BF(i+1,j-1))+(BF(i+1,j))+(BF(i+1,j+1)))/8;

i = 241; 
j = 225; 
BF(i,j) = ((BF(i-1,j-1))+(BF(i-1,j))+(BF(i-1,j+1))+(BF(i,j-1))+(BF(i,j+1))+(BF(i+1,j-1))+(BF(i+1,j))+(BF(i+1,j+1)))/8;

i = 273; 
j = 289; 
BF(i,j) = ((BF(i-1,j-1))+(BF(i-1,j))+(BF(i-1,j+1))+(BF(i,j-1))+(BF(i,j+1))+(BF(i+1,j-1))+(BF(i+1,j))+(BF(i+1,j+1)))/8;

i = 257; 
j = 259; 
BF(i,j) = ((BF(i-1,j-1))+(BF(i-1,j))+(BF(i-1,j+1))+(BF(i,j-1))+(BF(i,j+1))+(BF(i+1,j-1))+(BF(i+1,j))+(BF(i+1,j+1)))/8;

i = 241;
j = 289; 
BF(i,j) = ((BF(i-1,j-1))+(BF(i-1,j))+(BF(i-1,j+1))+(BF(i,j-1))+(BF(i,j+1))+(BF(i+1,j-1))+(BF(i+1,j))+(BF(i+1,j+1)))/8;

boyNoisyCleaned = uint8(ifft2(ifftshift(BF),'symmetric')); 
figure, 
subplot(1,2,1), imshow(boyNoisy), title('Boy (Noisy)'); 
subplot(1,2,2), imshow(boyNoisyCleaned), title('Boy (Cleaned)');

disp('For this problem, the four largest distinct values of the magnitude were chosen to do the processing because these highest pixels represent the cosine noise.')
pause;


%%%%%%%%%%%%%%%%%%%%%% ----- Problem 5 I ------ %%%%%%%%%%%%%%%%%%%%%%%

Lena = imread('Lena.jpg'); 
dLevel = wmaxlev(size(Lena),'db2');
[C,S] = wavedec2(Lena,dLevel,'db2');
reconsLena = uint8(waverec2(C,S,'db2'));
if reconsLena==Lena
  disp('Images are same!'); 
else
  disp('Images are NOT same!'); 
end
pause;


%%%%%%%%%%%%%%%%%%%%% ----- Problem 5 II ------ %%%%%%%%%%%%%%%%%%%%%%%

dwtmode('per');

[cA1,cH1,cV1,cD1] = dwt2(Lena,'db2'); 
[cA2,cH2,cV2,cD2] = dwt2(cA1,'db2'); 
[cA3,cH3,cV3,cD3] = dwt2(cA2,'db2'); 

[Height,Width] = size(cA3); 
Z = zeros(Height,Width); 
L1 = idwt2(Z,cH3,cV3,cD3,'db2'); 
L2 = idwt2(L1,cH2,cV2,cD2,'db2'); 
op = idwt2(L2,cH1,cV1,cD1,'db2'); 
op = uint8(op);

[Height1,Width1] = size(cH2); 
Z1 = zeros(Height1,Width1); 
L11 = idwt2(cA3,cH3,cV3,cD3, 'db2'); 
L12 = idwt2(L11,Z1,cV2,cD2,'db2'); 
op1 = uint8(idwt2(L12,cH1,cV1,cD1,'db2'));

figure,
subplot(1,2,1); imshow(op, []), title('Approx coefficients as 0s');
subplot(1,2,2); imshow(op1), title('HL2 as 0s');
pause;


%%%%%%%%%%%%%%%%%%%%% ----- Problem 6 ------ %%%%%%%%%%%%%%%%%%%%%%%%%

LenaNoisy = imnoise(Lena, 'gaussian', 0, 0.01);

[CA12,CH12,CV12,CD12] = dwt2(LenaNoisy,'db2'); 
[CA22,CH22,CV22,CD22] = dwt2(CA12,'db2'); 
[CA32,CH32,CV32,CD32] = dwt2(CA22,'db2');

L1 = [CH12,CV12,CD12]; 
S1 = median(median(abs(L1)))/(0.6745); 

CH12NumEle = numel(CH12);  
CV12NumEle = numel(CV12); 
CD12NumEle = numel(CD12); 

T1 = S1*sqrt(2*log(CH12NumEle+CV12NumEle+CD12NumEle)); 

for i=1:size(L1,2)/3
    for j=1:size(L1,2)
        if L1(i,j)>=T1 
            L1(i,j)=L1(i,j)-T1;
        elseif L1(i,j)<=-T1
            L1(i,j)=L1(i,j)+T1; 
        elseif abs(L1(i,j))< T1
            L1(i,j) = 0;
        end
    end
end 

CH12 = L1(1:size(L1,1), 1:size(L1,1)); 
CV12 = L1(1:size(L1,1), size(L1,1)+1:size(L1,1)*2); 
CD12 = L1(1:size(L1,1), (size(L1,1)*2)+1:size(L1,1)*3); 

L2 = [CH22,CV22,CD22]; 
S2 = median(median(abs(L2)))/(0.6745); 

CH22NumEle = numel(CH22);  
CV22NumEle = numel(CV22); 
CD22NumEle = numel(CD22); 

T2 = S2*sqrt(2*log(CH22NumEle+CV22NumEle+CD22NumEle)); 

for i=1:size(L2,2)/3
    for j=1:size(L2,2)
        if L2(i,j)>=T2 
           L2(i,j)=L2(i,j)-T2;
        elseif L2(i,j)<=-T2
            L2(i,j)=L2(i,j)+T2;
        elseif L2(i,j)<T2
            L2(i,j)=0;
        end            
    end
end

CH22 = L2(1:size(L2,1), 1:size(L2,1)); 
CV22 = L2(1:size(L2,1), size(L2,1)+1:size(L2,1)*2); 
CD22 = L2(1:size(L2,1), (size(L2,1)*2)+1:size(L2,1)*3); 

L3 = [CH32,CV32,CD32];
S3 = median(median(abs(L3)))./(0.6745);

CH32NumEle = numel(CH32);  
CV32NumEle = numel(CV32); 
CD32NumEle = numel(CD32); 

T3 = S3*sqrt(2*log(CH32NumEle+CV32NumEle+CD32NumEle)); 

for i=1:size(L3,2)/3
    for j=1:size(L3,2)
        if L3(i,j)>=T3 
           L3(i,j)=L3(i,j)-T3;
        elseif L3(i,j)<=-T3
            L3(i,j)=L3(i,j)+T3;
        elseif L3(i,j)<T3
            L3(i,j)=0;
        end            
    end
end

CH32 = L3(1:size(L3,1), 1:size(L3,1)); 
CV32 = L3(1:size(L3,1), size(L3,1)+1:size(L3,1)*2); 
CD32 = L3(1:size(L3,1), (size(L3,1)*2)+1:size(L3,1)*3);  

iCA2 = idwt2(CA32,CH32,CV32,CD32,'db2'); 
iCA1 = idwt2(iCA2,CH22,CV22,CD22,'db2'); 
oLena = uint8(idwt2(iCA1,CH12,CV12,CD12,'db2'));
figure, 
subplot(1,2,1), imshow(LenaNoisy), title('Noisy Lena');
subplot(1,2,2), imshow(oLena), title('De-Noised Lena'); 

clear;


