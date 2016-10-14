// table d'occurence pour chaque char
// table d'occurence pour les bigramme : tableau à double entrée.
// .txt expliquer la démarche
#include <string.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

#define LG_MAX 4096

typedef struct {
	int occurs;
	int tab[26];
} char_occur;

int pos(char a) {
	return ((int) a) - 97;
}

int asciiPos(char a) {
	return ((int) a) + 97;
}

int checkMin(char a) {
	return a >= 'a' && a <= 'z';
}

void lecture_texte(char message[LG_MAX], char_occur tab[26]) {
	for (int i = 0; i < strlen(message); i++) {
		printf("%s", message[i]);
		if (checkMin(message[i])) {
			tab[pos(message[i])].occurs++;
			if (i < strlen(message) - 1 && checkMin(message[i + 1]))
				tab[pos(message[i])].tab[pos(message[i + 1])]++;
		}
	}
}

int max(char_occur tab[26]) {
	int k = 0, nbApparitions = 0;
	char_occur tmp;
	for (int i = 0; i < 26; i++) {
		tmp = tab[i];
		if (tmp.occurs > nbApparitions) {
			k = i;
			nbApparitions = tmp.occurs;
		}
	}
	return asciiPos(k);
}

void initTab(char_occur tab[26]) {
	for (int i = 0; i < 26; i++) {
		tab[i].occurs = 5;
		for (int j = 0; j < 26; j++)
			tab[i].tab[j] = 0;
	}
}

void affiche(char_occur tab[26]) {
	for (int i = 0; i < 26; i++) {
		printf("char '%s' :\n\toccurs = %d\n", asciiPos(i), tab[i].occurs);
	}
}

void main() {
	char_occur tab[26];
	char message[LG_MAX] =
			"ubcfb osu ymoqsuu n cxqfj dqmfnu ub vjcfqu juz amqjmruz zmsscfusb bquflu auoquz hfszbms zwfba ju wusbms qusbqu ncsz ju vmo z uddmqvcfb n uxfbuq ju xusb wcoxcfz fj eczzc qcefnuwusb jc emqbu xfbquu no ijmv nuz wcfzmsz nu jc xfvbmfqu ecz czzul qcefnuwusb vueusncsb emoq uweuvauq kou z usrmoddqu us wuwu buwez kou jof os bmoqifjjms nu emozzfuqu ub nu zciju ju acjj zusbcfb ju vamo vofb ub ju xfuog bcefz c j osu nu zuz ugbquwfbuz osu cddfvau nu vmojuoq bqme xczbu emoq vu nuejmfuwusb fsbuqfuoq ubcfb vjmouu co woq ujju quequzusbcfb zfwejuwusb os usmqwu xfzcru jcqru nu ejoz n os wubqu ju xfzcru n os amwwu n usxfqms kocqcsbu vfsk csz c j uecfzzu wmozbcvau smfqu cog bqcfbz cvvusbouz ub iucog hfszbms zu nfqfruc xuqz j uzvcjfuq fj ubcfb fsobfju n uzzcpuq nu equsnqu j czvuszuoq wuwu cox wufjjuoquz uemkouz fj dmsvbfmsscfb qcquwusb cvboujjuwusb n cfjjuoqz ju vmoqcsb ujuvbqfkou ubcfb vmoeu ncsz jc ymoqsuu v ubcfb osu nuz wuzoquz n uvmsmwfu eqfzuz us xou nu jc zuwcfsu nu jc acfsu zms ceecqbuwusb ubcfb co zuebfuwu hfszbms kof cxcfb bqusbu suod csz ub zmoddqcfb n os ojvuqu xcqfkouog co nuzzoz nu jc vauxfjju nqmfbu wmsbcfb jusbuwusb fj z cqqubc ejozfuoqz dmfz us vauwfs emoq zu quemzuq c vackou ecjfuq zoq osu cddfvau vmjjuu co woq dcvu c jc vcru nu j czvuszuoq j usmqwu xfzcru xmoz dfgcfb no qurcqn v ubcfb os nu vuz emqbqcfbz cqqcsruz nu bujju zmqbu kou juz puog zuwijusb zofxqu vujof kof eczzu osu jurusnu zmoz ju emqbqcfb nfzcfb ifr iqmbauq xmoz qurcqnu c j fsbuqfuoq nu j ceecqbuwusb nu hfszbms osu xmfg zovquu dcfzcfb usbusnqu osu zuqfu nu smwiquz kof cxcfusb bqcfb c jc eqmnovbfms nu jc dmsbu jc xmfg eqmxuscfb n osu ejckou nu wubcj mijmsrou wfqmfq buqsu usvczbqu ncsz ju woq nu nqmfbu hfszbms bmoqsc os imobms ub jc xmfg nfwfsoc nu xmjowu wcfz juz wmbz ubcfusb usvmqu nfzbfsvbz ju zms nu j ceecqufj no bujuvqcs vmwwu ms nfzcfb emoxcfb ubqu czzmoqnf wcfz fj s p cxcfb covos wmpus nu j ubufsnqu vmwejubuwusb hfszbms zu nfqfruc xuqz jc dusubqu fj ubcfb nu zbcboqu dquju ejobmb eubfbu ub zc wcfrquoq ubcfb zmojfrsuu ecq jc vmwifscfzms ijuou osfdmqwu no ecqbf fj cxcfb juz vauxuog bquz ijmsnz ju xfzcru scboqujjuwusb zcsrofs jc euco noqvfu ecq ju zcxms rqmzzfuq juz jcwuz nu qczmfq uwmozzuuz ub ju dqmfn nu j afxuq kof xuscfb nu equsnqu dfs";

	initTab(tab);

	//lecture_texte(message);

	affiche(tab);

	printf("char 'e' = %s\n", max(tab));

	printf("%s\n", message);
}
