import binascii
import gmpy2
from Crypto.Util.number import long_to_bytes
import itertools

# Asumiendo que tienes los valores de pa, qa y n

n = 29586806204703503904475417475207181753327002572169029447649375105957401882782215640389377032462817994613047778235772651386166796847219215122193608670234881327146836245403865130095264430525949490627293009706522463181433619821350390053231766788878588019803892913863467778158401211594908728954466777191969075743630625982158807081765919372941602073557218037478580356881662021243200673073852880491273053505697020895498345529148573416671254161794089929501104359373518538647827148046556976153089011388743758537265917384291901943997429516622313174503298901194803857993394317539814737302849657303050354317604050790695336740197
e = 65537
c = 289237052906418762884047790698588237216944716119999663693817978740517247436542348526512736091707875268485882959243838075957586448819573713539185710260184827219075374475450076067646439475390463812726276515909921210433367783319773213058281003713061794012491199256342238837374855481171699583586743983649674740012967703537758965642137417249122001301052933823764400800217387933593716566296035172293554205353516931644458460946077324909646046976791514867691035326690183656159127063317802379729699302744042665121460855233651243384608623425524055255442028642152655550937674954968449428728770257685555116205296955850397292871
r = 171408814979432096422083612948586055966843837936163658129476465891245779338201428909963174351502472608440245083107238844857599433510467956593218402039143503630828490764200806210004441049988510271477183702664230548328047781827044594591096205229638868934484648515378149217164153158043694483551859654882276490099



# mask      = 0101 0101 0101 ...0101 and p
# mask << 1 = 1010 1010 1010 ...1010 and q
# r = (p & mask) + (q &(mask << 1))
# Al sumarse no va a haber carry, porque los bits de p & mask y q & mask << 1 no se solapan


pa = ""
qa = ""
i = 0
for elem in bin(r).lstrip("0b"):
    if i % 2 == 0:
        pa+= elem
        qa+= '0'
    else:
        qa+= elem
        pa+= '0'
    i += 1



    pa_low = pa[-16:]
    qa_low = qa[-16:]


    LL = ['0','1']*4

    mascara = itertools.permutations(LL,8)
    for perm in mascara:
        print(perm)
    






        


phi = (pa-1)*(qa-1)
d = pow(e,-1,phi)
plaintext = pow(c,d,n)
flag = long_to_bytes(plaintext)

print(f"\n\n{flag =}")