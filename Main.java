import java.util.Arrays;
import java.util.Scanner;

public class Main {

    int [] arreglo1 = new int [128];
    int [] arreglo2 = new int[128];
    int [] arreglo3 = new int[128];
    int [] arreglo4 = new int[128];
    int [] arreglo5 = new int[128];
    int [] arreglo6 = new int[128];
    int [] arreglo7 = new int[128];
    public int ultimaCarta;



    public static void  main (String args[]){

        Main main2= new Main();
        Scanner leer = new Scanner(System.in);
        int numJ, nbot;
        int sentidoJuego;
        int opc1;
        int turno=1;
        int terminar=0;
        int ronda=1;
        int sentido=0;
        while(terminar==0){
            System.out.println("¿Que quiere hacer?");
            System.out.println("1-Jugar");
            System.out.println("2-salir");
            opc1 = leer.nextInt();
            if (opc1 == 1) {
                System.out.println("ingresa numero de jugadores");
                numJ = leer.nextInt();
                int pb = 4 - numJ;
                if (numJ <= 4) {
                    System.out.println("ingresa numero de bots (min 0 y maximo" + pb + ")");
                    nbot = leer.nextInt();
                    if (nbot <= pb) {
                        main2.numero_jugadores();
                        main2.jugar_normal(numJ,nbot,turno,ronda,sentido);
                    } else {
                        System.out.println("ingrese un valor valido");
                    }
                }
                else{
                    System.out.println("ingrese un valor permitido");
                }
            }
            else if (opc1 == 2) {
                System.out.println("juego terminado");
                terminar = 1;
            }
            else{
                System.out.println("valor invalido");
            }
        }
    }

    public  void numero_jugadores(){
        for(int i=0;i<128;i++){
            arreglo1[i]=-5;
            arreglo2[i]=-5;
            arreglo3[i]=-5;
            arreglo4[i]=-5;
            arreglo5[i]=-5;
            arreglo6[i]=-5;
            arreglo7[i]=-5;
        }
    }

    public  int dame_carta(int ronda) {
        //10 reversa
        //11 no juegas
        int barajaCompleta[]= new int[129];
        int barajaDinamica[]= new int[129];
        int a;
        int value = -5;
        int t = 0;

        if(ronda==1){
            int x = 0;
            for (int i = 1; i < 121; i++) {
                barajaCompleta[i] = x;
                x++;
                if (x == 10) {
                    x = 0;
                }
                System.out.print(barajaCompleta[i]+", ");
            }

            for (int i = 121; i < 125; i++) {
                barajaCompleta[i] = 10;
            }
            for (int i = 125; i < 129; i++) {
                barajaCompleta[i] = 11;
            }
            Arrays.sort(barajaCompleta);
            for (int i = 1; i < 129; i++) {
                barajaDinamica[i]=barajaCompleta[i];
            }

        }
        while(t==0) {//baraja del jugador
            a = (int) (Math.random() * 128 + 1);
            t++;
            if (barajaDinamica[a] != -5) {
                value = barajaDinamica[a];
                barajaDinamica[a] = -5;
                return value;

            } else {
                t--;
            }
        }
        return value;
    }

    public void cartasInicio(int arreglo[]){
        int a;
        int ronda=1;
        for(int i=0;i<7;i++){
            a=dame_carta(ronda);
            arreglo[i]=a;
        }
    }
    public int imprimeCartas(int[] arreglo){
        int nc=0;
        for(int i=0;i<128;i++) {
            if (arreglo[i] != -5) {
                System.out.print(arreglo[i] + ",");
                nc++;
            }
        }
        return nc;
    }

    public int validaExiste(int[] arreglo, int carta){
        int nc=0;
        for(int i=0;i<128;i++){
            if (arreglo[i]!=-5){
                if(arreglo[i]==carta){
                    System.out.println("carta existente");
                    arreglo[i] =-5;
                    ultimaCarta = carta;
                    return 0;
                }
            }
        }
        System.out.println("carta no existente, ingresa una valida");
        return 1;
    }

    public void jugar_normal(int numJ,int nbot,int turno,int ronda,int sentido) {
        int ganador = 0;//0 es que no hay ganador
        sentido=0;

        if (ronda==1){
            cartasInicio(arreglo1);
            if(numJ>=2){
                cartasInicio(arreglo2);
                if (numJ >= 3){
                    cartasInicio(arreglo3);
                }
                if (numJ == 4){
                    cartasInicio(arreglo4);
                }
                if (nbot >= 1){
                    cartasInicio(arreglo5);
                }
                if (nbot>=2){
                    cartasInicio(arreglo6);
                }if (nbot>=3){
                    cartasInicio(arreglo7);
                }
            }
        }

        while (ganador == 0) {
            switch (turno) {
                case 1:
                    System.out.println("turno "+turno);
                        sentido=juegoPlayer(arreglo1, ronda,sentido);
                        ganador=terminarJuego(arreglo1);
                    if (sentido==1){
                        jugar_inverso(numJ,nbot,turno,ronda,sentido);
                    }
                        turno++;
                    ronda++;
                    break;
                case 2:
                    System.out.println("turno "+turno);
                    if(nbot>=1) {
                        sentido=juegoBot(arreglo5, ronda,sentido);
                        ganador=terminarJuego(arreglo5);
                        if (sentido==1){
                            jugar_inverso(numJ,nbot,turno,ronda,sentido);
                        }
                    }
                    turno++;
                    break;
                case 3:
                    System.out.println("turno "+turno);
                    if(numJ>=2) {
                        sentido=juegoPlayer(arreglo2, ronda,sentido);
                        ganador=terminarJuego(arreglo2);
                        if (sentido==1){
                            jugar_inverso(numJ,nbot,turno,ronda,sentido);
                        }
                    }
                    turno++;
                    break;
                case 4:
                    System.out.println("turno "+turno);
                    if(nbot>=2) {
                        sentido=juegoBot(arreglo6, ronda,sentido);
                        ganador=terminarJuego(arreglo6);
                        if (sentido==1){
                            jugar_inverso(numJ,nbot,turno,ronda,sentido);
                        }
                    }
                    turno++;
                    break;
                case 5:
                    System.out.println("turno "+turno);
                    if(numJ>=3) {
                        sentido=juegoPlayer(arreglo3, ronda,sentido);
                        ganador=terminarJuego(arreglo3);
                        if (sentido==1){
                            jugar_inverso(numJ,nbot,turno,ronda,sentido);
                        }
                    }
                    turno++;
                    break;
                case 6:
                    System.out.println("turno "+turno);
                    if(nbot==3) {
                        sentido=juegoBot(arreglo7, ronda,sentido);
                        ganador=terminarJuego(arreglo7);
                        if (sentido==1){
                            jugar_inverso(numJ,nbot,turno,ronda,sentido);
                        }
                    }
                    turno++;
                    break;
                case 7:
                    System.out.println("turno "+turno);
                    if(numJ>=4) {
                        sentido=juegoPlayer(arreglo4, ronda,sentido);
                        ganador=terminarJuego(arreglo4);
                        if (sentido==1){
                            jugar_inverso(numJ,nbot,turno,ronda,sentido);
                        }
                    }
                    turno=1;
                    break;
                default:
                    turno = 1;
            }

        }
    }

    public void jugar_inverso(int numJ,int nbot, int turno, int ronda,int sentido){
        switch(turno) {
            case 1:
                System.out.println("turno "+turno);
                sentido=juegoPlayer(arreglo1,ronda,sentido);
                terminarJuego(arreglo1);
                if (sentido==0){
                    jugar_normal(numJ,nbot,turno,ronda,sentido);
                }
                turno=7;
                break;
            case 2:
                System.out.println("turno "+turno);
                if(nbot>=1) {
                    sentido=juegoBot(arreglo5, ronda,sentido);
                    terminarJuego(arreglo5);
                }
                turno--;
                break;
            case 3:
                System.out.println("turno "+turno);
                if(numJ>=2) {
                    sentido=juegoPlayer(arreglo2, ronda,sentido);
                    terminarJuego(arreglo2);
                }
                turno--;
                break;
            case 4:
                System.out.println("turno "+turno);
                if(nbot>=2) {
                    sentido=juegoBot(arreglo6, ronda,sentido);
                    terminarJuego(arreglo6);
                }
                    turno--;
                break;
            case 5:
                System.out.println("turno "+turno);
                if(numJ>=3) {
                    sentido=juegoPlayer(arreglo3, ronda,sentido);
                    terminarJuego(arreglo3);
                }
                turno--;
                break;
            case 6:
                System.out.println("turno "+turno);
                if(nbot==3) {
                    sentido=juegoBot(arreglo7, ronda,sentido);
                    terminarJuego(arreglo7);
                }
                turno--;
                break;
            case 7:
                System.out.println("turno "+turno);
                if(numJ>=4) {
                    sentido=juegoPlayer(arreglo4, ronda,sentido);
                    terminarJuego(arreglo4);
                }
                turno--;
                break;
            default:
            turno=7;
        }
    }
    public int juegoPlayer(int[] arreglo,int ronda, int sentido){
    Scanner leer=new Scanner(System.in);
        int a = 0;
        int cartaJ;
        int g;
        int respVC=1;//valor que dice si la carta existe
        int huboCarta=1;
        int nc=0;
        if(ronda==1){
            System.out.println("");
            nc=imprimeCartas(arreglo);
            System.out.println("\nque carta desea tirar?");
            cartaJ= leer.nextInt();
            respVC=validaExiste(arreglo,cartaJ);
            if (respVC==0){
                    System.out.println("carta valida");
                    ultimaCarta=cartaJ;
                    huboCarta=0;
                    if(ultimaCarta==10){
                        if(sentido==1){
                            sentido=0;

                        }
                        else{
                            sentido=1;
                        }

                    }
            }
        }
        else if(ronda!=1){
            nc=imprimeCartas(arreglo);
            System.out.println("que carta desea tirar?");
            cartaJ= leer.nextInt();
            respVC=validaExiste(arreglo,cartaJ);
            if (respVC==0){
                if (cartaJ == ultimaCarta || cartaJ == ultimaCarta + 1 || cartaJ == ultimaCarta - 1 && cartaJ != -5 || cartaJ == 10 || cartaJ == 11|| cartaJ == 9 && ultimaCarta==0 || cartaJ == 0&& ultimaCarta==9) {
                    nc=imprimeCartas(arreglo);
                    System.out.println("carta valida");
                    ultimaCarta=cartaJ;
                    huboCarta=0;
                    if(ultimaCarta==10){
                        if(sentido==1){
                            sentido=0;
                        }
                        else{
                            sentido=1;
                        }
                    }
                }
            }
        }

        cartasRestantes(nc);
           return sentido;
    }
    public int juegoBot(int[] arreglo, int ronda, int sentido){
        int huboCarta=0;
        int a;
        int nc=0;
        for(int i=0;i<128;i++){
            if (arreglo[i]!=-1) {

                if (arreglo[i] == ultimaCarta || arreglo[i] == ultimaCarta + 1 || arreglo[i] == ultimaCarta - 1 && arreglo[i] != -5 || arreglo[i] == 10 || arreglo[i] == 11|| arreglo[i] == 9 && ultimaCarta==0 || arreglo[i] == 0&& ultimaCarta==9) {
                    nc=imprimeCartas(arreglo);
                    System.out.println("carta valida");
                    ultimaCarta=arreglo[i];
                    huboCarta=0;
                    if(ultimaCarta==10){
                        if(sentido==1){
                            sentido=0;
                        }
                        else{
                            sentido=1;
                        }
                    }
                }
            }
        }
        if(huboCarta==1) {
            System.out.println("el boot no tiene carta, pido nueva");
            a=1;
            for (int i = 0; i < 120; i++) {
                if (arreglo[i] == -1) {
                    if (a == 1) {
                        arreglo[i] = dame_carta(ronda);
                        huboCarta=1;
                        a=0;
                    }
                }
            }
        }
        return sentido;
    }

    public int terminarJuego(int[] arreglo){
        for(int i=0; i<arreglo.length; i++){
            if (arreglo[i]!=-1){
                return 0;
            }
        }
        return 1;
    }
    public void cartasRestantes(int nc){
        System.out.print("las cartas restantes son: ");
        for(int n=0;n<=nc;n++){
            System.out.print("x " );
        }
    }

    }
