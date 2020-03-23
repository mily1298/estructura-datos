package mx.unam.ciencias.edd;

/**
 * Clase para métodos estáticos con dispersores de bytes.
 */
public class Dispersores {

    /**
     * Función de dispersión XOR.
     * @param llave la llave a dispersar.
     * @return la dispersión de XOR de la llave.
     */
    public static int dispersaXOR(byte[] llave) {
    	int l,i,r,t=0;
    	for(l=llave.length,i=0,r=0;l>=4;l-=4,i+=4)
    		r^=((llave[i]&0xFF)<<24)|((llave[i+1]&0xFF)<<16)|((llave[i+2]&0xFF)<<8)|(llave[i+3] & 0xFF);

    	while(l-- > 0)
    		t |= ((llave[i+l] & 0xFF)<<(8*(3-l)));
    	return r^t;
    }

    /**
     * Función de dispersión de Bob Jenkins.
     * @param llave la llave a dispersar.
     * @return la dispersión de Bob Jenkins de la llave.
     */
    public static int dispersaBJ(byte[] llave) {
    	int l=llave.length;
    	int a= 0x9e3779b9;
    	int b= 0x9e3779b9;
    	int c= 0xFFFFFFFF;
    	int indice=0;
    	while(l>=12){
    		a+=(llave[indice] & 0xFF) | ((llave[indice+1] & 0xFF)<<8) | ((llave[indice+2] & 0xFF)<<16) | ((llave[indice+3])<<24);
    		b+=(llave[indice+4] & 0xFF) | ((llave[indice+5] & 0xFF)<<8) | ((llave[indice+6] & 0xFF)<<16) | ((llave[indice+7] & 0xFF)<<24);
    		c+=(llave[indice+8] & 0xFF) | ((llave[indice+9] & 0xFF)<<8) | ((llave[indice+10] & 0xFF)<<16) | ((llave[indice+11] & 0xFF)<<24);

    		a-=b;	a-=c;	a^=(c>>>13);
    		b-=c;	b-=a;	b^=(a<<8);
    		c-=a;	c-=b;	c^=(b>>>13);
    		a-=b;	a-=c;	a^=(c>>>12);
    		b-=c;	b-=a;	b^=(a<<16);
    		c-=a;	c-=b;	c^=(b>>>5);
    		a-=b;	a-=c;	a^=(c>>>3);
    		b-=c;	b-=a;	b^=(a<<10);
    		c-=a;	c-=b;	c^=(b>>>15);
    		l-=12;
    		indice+=12;
    	}
    	c+=llave.length;
    	switch(l){
    		case(11): c+=((llave[indice+10]&0xFF)<<24);
    		case(10): c+=((llave[indice+9]&0xFF)<<16);
    		case(9): c+=((llave[indice+8]&0xFF)<<8);
    		case(8): b+=((llave[indice+7]&0xFF)<<24);
    		case(7): b+=((llave[indice+6]&0xFF)<<16);
    		case(6): b+=((llave[indice+5]&0xFF)<<8);
    		case(5): b+=(llave[indice+4]&0xFF);
    		case(4): a+=((llave[indice+3]&0xFF)<<24);
    		case(3): a+=((llave[indice+2]&0xFF)<<16);
    		case(2): a+=((llave[indice+1]&0xFF)<<8);
    		case(1): a+=(llave[indice]&0xFF);

    	}

    	a-=b;	a-=c;	a^=(c>>>13);
    	b-=c;	b-=a;	b^=(a<<8);
    	c-=a;	c-=b;	c^=(b>>>13);
    	a-=b;	a-=c;	a^=(c>>>12);
    	b-=c;	b-=a;	b^=(a<<16);
    	c-=a;	c-=b;	c^=(b>>>5);
    	a-=b;	a-=c;	a^=(c>>>3);
    	b-=c;	b-=a;	b^=(a<<10);
    	c-=a;	c-=b;	c^=(b>>>15);

    	return c;
    }

    /**
     * Función de dispersión Daniel J. Bernstein.
     * @param llave la llave a dispersar.
     * @return la dispersión de Daniel Bernstein de la llave.
     */
    public static int dispersaDJB(byte[] llave) {
    	int h=5381;
    	for(int i=0;i<llave.length;i++)
    		h+=(h<<5)+(llave[i]&0xFF);
    	return h;
    }
}
