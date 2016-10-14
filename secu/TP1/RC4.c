#include<stdio.h>
#include<stdint.h>
#include<fcntl.h>

// Nogues Maël & Grandmontagne Mathieu

void swap(uint8_t *a, uint8_t *b){
	//swap value of a and b

	uint8_t tmp = *a;
	*a = *b;
	*b = tmp;
}

uint8_t array_search(uint8_t S[256], uint8_t val){
	//search value "val" in the array "S"

	for (int i=0; i < 256; i++)
		if (S[i]==val)
			return i;

	return 0;
}

void ivGen(uint8_t iv[16]){
	//Generate random iv
	
	int byte_count = 16;
	uint8_t data[16];
	FILE *fp;
	fp = fopen("/dev/urandom", "r");
	fread(&data, 1, byte_count, fp);
	fclose(fp);
	
	
	for(int i = 0; i < 16; i++){
		iv[i] = data[i];
	}
}

void ivGen_FixByte01(uint8_t iv[16], uint8_t byte0, uint8_t byte1){
	//Generate random iv but with iv[0] = byte0 and iv[1] = byte1
	// /!\ ONLY HERE FOR EASIER ATTACK, NOT GOOD IN GENERAL /!\
	
	int byte_count = 16;
	uint8_t data[16];
	FILE *fp;
	fp = fopen("/dev/urandom", "r");
	fread(&data, 1, byte_count, fp);
	fclose(fp);
	
	
	for(int i = 0; i < 16; i++){
		iv[i] = data[i];
	}
	iv[0] = byte0;
	iv[1] = byte1;
}

void initialisation(uint8_t K[256], uint8_t S[256]){
	//Initialize S with K

	for (int i = 0; i < 256; i++)
		S[i] = i;

	uint8_t j = 0;
	for (int i = 0; i < 256; i++){
		j = (j + S[i] + K[i % 16]) % 256; // j = j + S[i] + K[i % 256];
		swap(&S[i], &S[j]);
	}
}

void RC4(uint8_t *message, uint32_t len_message, uint8_t key[16], uint8_t iv[16], uint8_t *out){
	//Encrypt "message" of length "len_message" using "key" and "iv", and put it in "out"

	uint8_t i = 0, j = 0, index, z, S[256], K[256];

	for (int k = 0; k < 16; k++)
		K[k] = iv[k];

	for (int k = 16; k < 256; k++)
		K[k] = key[k % 16];

	initialisation(K, S);
	
	for (uint32_t ctr = 0; ctr < len_message; ctr++) {
		i = (i + 1) % 256;
		j = (j + S[i]) % 256;
		swap(&S[i], &S[j]);
		index = S[i] + S[j];
		z = S[index];
		out[ctr] = z ^ message[ctr];
	}
		
	// Not finished
}

int main(){
	
	uint8_t message[16] = {0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x99, 0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff};
	uint8_t out[16];
	uint32_t len_message = 16;
	
	uint8_t key[16] = {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
	uint8_t iv[16] = {0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19, 0x1a, 0x1b, 0x1c, 0x1d, 0x1e, 0x1f};

	RC4(message, len_message, key, iv, out);
	
	printf("Message : ");
	for(int i = 0; i < len_message; i++){
		printf("%02x ",message[i]);
	}
	printf("\n\n");
	
	printf("Key     : ");
	for(int i = 0; i < 16; i++){
		printf("%02x ",key[i]);
	}
	printf("\n\n");
	
	printf("IV      : ");
	for(int i = 0; i < 16; i++){
		printf("%02x ",iv[i]);
	}
	printf("\n\n");
	
	printf("Cipher  : ");
	for(int i = 0; i < len_message; i++){
		printf("%02x ",out[i]);
	}
	printf("\n");

	return 0;
}
