package com.example.carrun;

import static android.opengl.GLES10.glTranslatef;

import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

public class ObstCar {

    public float xCoord, yCoord, zCoord;

    public float[] transformationMatrix;
    public float[] velocity;

    private float r,g,b = 0.0f;



    boolean stopped = false;
    float speed;



    private FloatBuffer bottomvertexBuffer;
    private FloatBuffer bottomcolorBuffer;
    private ShortBuffer bottomtopoBuffer;

    // frontstoßstange

    private FloatBuffer stossvertexBuffer;
    private FloatBuffer stosscolorBuffer;
    private ShortBuffer stosstopoBuffer;

    //Heck
    private FloatBuffer backvertexBuffer;
    private FloatBuffer backcolorBuffer;
    private ShortBuffer backtopoBuffer;

    //Left

    private FloatBuffer leftvertexBuffer;
    private FloatBuffer leftcolorBuffer;
    private ShortBuffer lefttopoBuffer;

    //Right

    private FloatBuffer rightvertexBuffer;
    private FloatBuffer rightcolorBuffer;
    private ShortBuffer righttopoBuffer;

    private FloatBuffer backglassVertexBuffer;
    private FloatBuffer backglassColorBuffer;
    private ShortBuffer backglassTopoBuffer;

    private FloatBuffer backglassblackVertexBuffer;
    private FloatBuffer backglassblackColorBuffer;
    private ShortBuffer backglassblackTopoBuffer;

    private FloatBuffer frontglassVertexBuffer;
    private FloatBuffer frontglassColorBuffer;
    private ShortBuffer frontglassTopologyBuffer;


    private FloatBuffer leftglassVertexBuffer;
    private FloatBuffer leftglassColorBuffer;
    private ShortBuffer leftglassTopologyBuffer;


    private FloatBuffer leftglassblackVertexBuffer;
    private FloatBuffer leftglassblackColorBuffer;
    private ShortBuffer leftglassblackTopologyBuffer;

    private FloatBuffer frontglassblackVertexBuffer;
    private FloatBuffer frontglassblackColorBuffer;
    private ShortBuffer frontglassblackTopologyBuffer;


    private FloatBuffer rightglassVertexBuffer;
    private FloatBuffer rightglassColorBuffer;
    private ShortBuffer rightglassTopologyBuffer;


    private FloatBuffer rightglassblackVertexBuffer;
    private FloatBuffer rightglassblackColorBuffer;
    private ShortBuffer rightglassblackTopologyBuffer;

    private FloatBuffer trunkVertexBuffer;
    private FloatBuffer trunkColorBuffer;
    private ShortBuffer trunkTopologyBuffer;


    private FloatBuffer hoodVertexBuffer;
    private FloatBuffer hoodColorBuffer;
    private ShortBuffer hoodTopologyBuffer;


    private FloatBuffer topVertexBuffer;
    private FloatBuffer topColorBuffer;
    private ShortBuffer topTopologyBuffer;


    private FloatBuffer frontglassupVertexBuffer;
    private FloatBuffer frontglassupColorBuffer;
    private ShortBuffer frontglassupTopologyBuffer;


    private FloatBuffer backlightLAvertexBuffer;
    private FloatBuffer backlightLAcolorBuffer;
    private ShortBuffer backlightLAtopoBuffer;

    private FloatBuffer backlightLBvertexBuffer;
    private FloatBuffer backlightLBcolorBuffer;
    private ShortBuffer backlightLBtopoBuffer;

    private FloatBuffer backlightRAvertexBuffer;
    private FloatBuffer backlightRAcolorBuffer;
    private ShortBuffer backlightRAtopoBuffer;


    private FloatBuffer backlightRBvertexBuffer;
    private FloatBuffer backlightRBcolorBuffer;
    private ShortBuffer backlightRBtopoBuffer;





    public ObstCar(int trail,int level){

        transformationMatrix = new float[16];
        velocity = new float[] {1.0f,1.0f,1.0f};

        Matrix.setIdentityM(transformationMatrix, 0);


        if(trail == 0){
            this.xCoord = -4.f;
        }else if(trail == 1){
            this.xCoord = 0.0f;
        }else{
            this.xCoord = 4.f;
        }

        Random rand = new Random();
        r = rand.nextFloat();
        g = rand.nextFloat();
        b = rand.nextFloat();

        switch(level){
            case 0:
                speed = 0.5f;
                break;
            case 1:
                speed = 0.6f;
                break;
            case 2:
                speed = 0.8f;
                break;
            case 3:
                speed = 1.1f;
                break;
            case 4:
                speed = 1.3f;
                break;
            default:
                break;
        }




        yCoord = 0.0f;
        zCoord = -90.0f;

        //speed = 0.9f;

        Matrix.translateM(transformationMatrix, 0, xCoord, yCoord, zCoord);

        float geometryBottom[] = {
                -1.5f, 0.0f, 5.0f,
                -1.5f, 0.0f, -4.0f,
                1.5f, 0.0f, 5.0f,
                1.5f, 0.0f, -4.0f};
        float colorBottom[] = {
                1.0f, 0.5f, 0.0f, 0.0f,
                1.0f, 0.5f, 0.0f, 0.0f,
                1.0f, 0.5f, 0.0f, 0.0f,
                1.0f, 0.5f, 0.0f, 0.0f};
        short topologyBottom[] = {
                2,0,1,
                2,1,3};

        ByteBuffer bottomVertexBB = ByteBuffer.allocateDirect(geometryBottom.length * 4);
        bottomVertexBB.order(ByteOrder.nativeOrder());
        bottomvertexBuffer = bottomVertexBB.asFloatBuffer();
        bottomvertexBuffer.put(geometryBottom);
        bottomvertexBuffer.position(0);

        ByteBuffer bottomColorBB = ByteBuffer.allocateDirect(colorBottom.length * 4);
        bottomColorBB.order(ByteOrder.nativeOrder());
        bottomcolorBuffer = bottomColorBB.asFloatBuffer();
        bottomcolorBuffer.put(colorBottom);
        bottomcolorBuffer.position(0);

        ByteBuffer bottomTopologyBB = ByteBuffer.allocateDirect(topologyBottom.length * 2);
        bottomTopologyBB.order(ByteOrder.nativeOrder());
        bottomtopoBuffer = bottomTopologyBB.asShortBuffer();
        bottomtopoBuffer.put(topologyBottom);
        bottomtopoBuffer.position(0);

        //stoßstange

        float geometryStoss[] = {
                -1.5f, 1.0f, 5.0f,
                -1.5f, 0.0f, 5.0f,
                1.5f, 1.0f, 5.0f,
                1.5f, 0.0f, 5.0f};



        float colorStoss[] = {
                r-0.2f, g, b, 0.0f,
                r-0.2f, g, b, 0.0f,
                r-0.2f, g, b, 0.0f,
                r-0.2f, g, b, 0.0f};
        short topologyStoss[] = {0,1,2,
                1,3,2};

        ByteBuffer stossVertexBB = ByteBuffer.allocateDirect(geometryStoss.length * 4);
        stossVertexBB.order(ByteOrder.nativeOrder());
        stossvertexBuffer = stossVertexBB.asFloatBuffer();
        stossvertexBuffer.put(geometryStoss);
        stossvertexBuffer.position(0);

        ByteBuffer stossColorBB = ByteBuffer.allocateDirect(colorStoss.length * 4);
        stossColorBB.order(ByteOrder.nativeOrder());
        stosscolorBuffer = stossColorBB.asFloatBuffer();
        stosscolorBuffer.put(colorStoss);
        stosscolorBuffer.position(0);

        ByteBuffer stossTopologyBB = ByteBuffer.allocateDirect(topologyStoss.length * 2);
        stossTopologyBB.order(ByteOrder.nativeOrder());
        stosstopoBuffer = stossTopologyBB.asShortBuffer();
        stosstopoBuffer.put(topologyStoss);
        stosstopoBuffer.position(0);

        //Heck

        float geometryBack[] = {
                -1.5f, 1.0f, -4.0f,
                -1.5f, 0.0f, -4.0f,
                1.5f, 1.0f, -4.0f,
                1.5f, 0.0f, -4.0f};
        float colorBack[] = {
                r-0.2f, g, b, 0.0f,
                r-0.2f, g, b, 0.0f,
                r-0.2f, g, b, 0.0f,
                r-0.2f, g, b, 0.0f};
        short topologyBack[] = {0,2,1,
                1,2,3};

        ByteBuffer backVertexBB = ByteBuffer.allocateDirect(geometryBack.length * 4);
        backVertexBB.order(ByteOrder.nativeOrder());
        backvertexBuffer = backVertexBB.asFloatBuffer();
        backvertexBuffer.put(geometryBack);
        backvertexBuffer.position(0);

        ByteBuffer backColorBB = ByteBuffer.allocateDirect(colorBack.length * 4);
        backColorBB.order(ByteOrder.nativeOrder());
        backcolorBuffer = backColorBB.asFloatBuffer();
        backcolorBuffer.put(colorBack);
        backcolorBuffer.position(0);

        ByteBuffer backTopologyBB = ByteBuffer.allocateDirect(topologyBack.length * 2);
        backTopologyBB.order(ByteOrder.nativeOrder());
        backtopoBuffer = backTopologyBB.asShortBuffer();
        backtopoBuffer.put(topologyBack);
        backtopoBuffer.position(0);


        //Left

        float geometryLeft[] = {
                -1.5f, 1.0f, -4.0f, 		// v4
                -1.5f, 0.0f, -4.0f,  	// v2
                -1.5f, 1.0f, 5.0f, 		// v4
                -1.5f, 0.0f, 5.0f};
        float colorLeft[] = {
                r-0.05f, g, b, 0.0f,
                r-0.05f, g, b, 0.0f,
                r-0.05f, g, b, 0.0f,
                r-0.05f, g, b, 0.0f};
        short topologyLeft[] = {1,2,0,
                2,1,3};						// v1, v5, v4

        ByteBuffer leftVertexBB = ByteBuffer.allocateDirect(geometryLeft.length * 4);
        leftVertexBB.order(ByteOrder.nativeOrder());
        leftvertexBuffer = leftVertexBB.asFloatBuffer();
        leftvertexBuffer.put(geometryLeft);
        leftvertexBuffer.position(0);

        ByteBuffer leftColorBB = ByteBuffer.allocateDirect(colorLeft.length * 4);
        leftColorBB.order(ByteOrder.nativeOrder());
        leftcolorBuffer = leftColorBB.asFloatBuffer();
        leftcolorBuffer.put(colorLeft);
        leftcolorBuffer.position(0);

        ByteBuffer leftTopologyBB = ByteBuffer.allocateDirect(topologyLeft.length * 2);
        leftTopologyBB.order(ByteOrder.nativeOrder());
        lefttopoBuffer = leftTopologyBB.asShortBuffer();
        lefttopoBuffer.put(topologyLeft);
        lefttopoBuffer.position(0);


        //Right
        float geometryright[] = {
                1.5f, 1.0f, -4.0f,
                1.5f, 0.0f, -4.0f,
                1.5f, 1.0f, 5.0f,
                1.5f, 0.0f, 5.0f};
        float colorright[] = {
                r-0.05f, g, b, 0.0f,
                r-0.05f, g, b, 0.0f,
                r-0.05f, g, b, 0.0f,
                r-0.05f, g, b, 0.0f};
        short topologyright[] = {2,1,0,
                1,2,3};

        ByteBuffer rightVertexBB = ByteBuffer.allocateDirect(geometryright.length * 4);
        rightVertexBB.order(ByteOrder.nativeOrder());
        rightvertexBuffer = rightVertexBB.asFloatBuffer();
        rightvertexBuffer.put(geometryright);
        rightvertexBuffer.position(0);

        ByteBuffer rightColorBB = ByteBuffer.allocateDirect(colorright.length * 4);
        rightColorBB.order(ByteOrder.nativeOrder());
        rightcolorBuffer = rightColorBB.asFloatBuffer();
        rightcolorBuffer.put(colorright);
        rightcolorBuffer.position(0);

        ByteBuffer rightTopologyBB = ByteBuffer.allocateDirect(topologyright.length * 2);
        rightTopologyBB.order(ByteOrder.nativeOrder());
        righttopoBuffer = rightTopologyBB.asShortBuffer();
        righttopoBuffer.put(topologyright);
        righttopoBuffer.position(0);

        float geometryBackglass[] = {
                -1.0f,2.0f,-1.0f,
                -1.5f,1.0f,-2.0f,
                1.0f,2.0f,-1.0f,
                1.5f,1.0f,-2.0f};
        float colorBackglass[] = {
                r-0.2f, g, b, 0.0f,
                r-0.2f, g, b, 0.0f,
                r-0.2f, g, b, 0.0f,
                r-0.2f, g, b, 0.0f};
        short topologyBackglass[] = {1,2,3,
                0,2,1};

        ByteBuffer backglassBB = ByteBuffer.allocateDirect(geometryBackglass.length * 4);
        backglassBB.order(ByteOrder.nativeOrder());
        backglassVertexBuffer = backglassBB.asFloatBuffer();
        backglassVertexBuffer.put(geometryBackglass);
        backglassVertexBuffer.position(0);

        ByteBuffer backglassColorBB = ByteBuffer.allocateDirect(colorBackglass.length * 4);
        backglassColorBB.order(ByteOrder.nativeOrder());
        backglassColorBuffer = backglassColorBB.asFloatBuffer();
        backglassColorBuffer.put(colorBackglass);
        backglassColorBuffer.position(0);

        ByteBuffer backglassTopologyBB = ByteBuffer.allocateDirect(topologyBackglass.length * 2);
        backglassTopologyBB.order(ByteOrder.nativeOrder());
        backglassTopoBuffer = backglassTopologyBB.asShortBuffer();
        backglassTopoBuffer.put(topologyBackglass);
        backglassTopoBuffer.position(0);

        float geometryfrontglass[] = {
                -1.0f,2.0f,1.0f,
                -1.5f,1.0f,2.0f,
                1.0f,2.0f,1.0f,
                1.5f,1.0f,2.0f,};
        float colorfrontglass[] = {
                r-0.2f, g, b, 0.0f,
                r-0.2f, g, b, 0.0f,
                r-0.2f, g, b, 0.0f,
                r-0.2f, g, b, 0.0f};
        short topologyfrontglass[] = {1,3,2,
                0,1,2 };

        ByteBuffer frontglassVertexBB = ByteBuffer.allocateDirect(geometryfrontglass.length * 4);
        frontglassVertexBB.order(ByteOrder.nativeOrder());
        frontglassVertexBuffer = frontglassVertexBB.asFloatBuffer();
        frontglassVertexBuffer.put(geometryfrontglass);
        frontglassVertexBuffer.position(0);

        ByteBuffer frontglassColorBB = ByteBuffer.allocateDirect(colorfrontglass.length * 4);
        frontglassColorBB.order(ByteOrder.nativeOrder());
        frontglassColorBuffer = frontglassColorBB.asFloatBuffer();
        frontglassColorBuffer.put(colorfrontglass);
        frontglassColorBuffer.position(0);

        ByteBuffer frontglassTopologyBB = ByteBuffer.allocateDirect(topologyfrontglass.length * 2);
        frontglassTopologyBB.order(ByteOrder.nativeOrder());
        frontglassTopologyBuffer = frontglassTopologyBB.asShortBuffer();
        frontglassTopologyBuffer.put(topologyfrontglass);
        frontglassTopologyBuffer.position(0);


        float geometryleftglass[] = {
                -1.0f,2.0f,1.0f,
                -1.5f,1.0f,2.0f,
                -1.0f,2.0f,-1.0f,
                -1.5f,1.0f,-2.0f};
        float colorleftglass[] = {
                r-0.1f, g, b, 0.0f,
                r-0.1f, g, b, 0.0f,
                r-0.1f, g, b, 0.0f,
                r-0.1f, g, b, 0.0f};
        short topologyleftglass[] = {2, 1, 0,
                1, 2, 3};

        ByteBuffer leftglassVertexBB = ByteBuffer.allocateDirect(geometryleftglass.length * 4);
        leftglassVertexBB.order(ByteOrder.nativeOrder());
        leftglassVertexBuffer = leftglassVertexBB.asFloatBuffer();
        leftglassVertexBuffer.put(geometryleftglass);
        leftglassVertexBuffer.position(0);

        ByteBuffer leftglassColorBB = ByteBuffer.allocateDirect(colorleftglass.length * 4);
        leftglassColorBB.order(ByteOrder.nativeOrder());
        leftglassColorBuffer = leftglassColorBB.asFloatBuffer();
        leftglassColorBuffer.put(colorleftglass);
        leftglassColorBuffer.position(0);

        ByteBuffer leftglassTopologyBB = ByteBuffer.allocateDirect(topologyleftglass.length * 2);
        leftglassTopologyBB.order(ByteOrder.nativeOrder());
        leftglassTopologyBuffer = leftglassTopologyBB.asShortBuffer();
        leftglassTopologyBuffer.put(topologyleftglass);
        leftglassTopologyBuffer.position(0);


        float geometryrightglass[] = {
                1.0f,2.0f,1.0f,
                1.5f,1.0f,2.0f,				// v5
                1.0f,2.0f,-1.0f,
                1.5f,1.0f,-2.0f }; 			// v1
        float colorrightglass[] = {
                r-0.1f, g, b, 0.0f,
                r-0.1f, g, b, 0.0f,
                r-0.1f, g, b, 0.0f,
                r-0.1f, g, b, 0.0f};	// v1
        short topologyrightglass[] = {2, 0, 3, 	// v0, v1, v4
                3, 0, 1};						// v1, v5, v4

        ByteBuffer rightglassVertexBB = ByteBuffer.allocateDirect(geometryrightglass.length * 4);
        rightglassVertexBB.order(ByteOrder.nativeOrder());
        rightglassVertexBuffer = rightglassVertexBB.asFloatBuffer();
        rightglassVertexBuffer.put(geometryrightglass);
        rightglassVertexBuffer.position(0);

        ByteBuffer rightglassColorBB = ByteBuffer.allocateDirect(colorrightglass.length * 4);
        rightglassColorBB.order(ByteOrder.nativeOrder());
        rightglassColorBuffer = rightglassColorBB.asFloatBuffer();
        rightglassColorBuffer.put(colorrightglass);
        rightglassColorBuffer.position(0);

        ByteBuffer rightglassTopologyBB = ByteBuffer.allocateDirect(topologyrightglass.length * 2);
        rightglassTopologyBB.order(ByteOrder.nativeOrder());
        rightglassTopologyBuffer = rightglassTopologyBB.asShortBuffer();
        rightglassTopologyBuffer.put(topologyrightglass);
        rightglassTopologyBuffer.position(0);


        float geometrytrunk[] = {
                -1.5f,1.0f,-2.0f,
                -1.5f, 1.0f, -4.0f,
                1.5f,1.0f,-2.0f,
                1.5f, 1.0f, -4.0f
        };
        float colortrunk[] = {
                r, g, b, 0.0f,
                r, g, b, 0.0f,
                r, g, b, 0.0f,
                r, g, b, 0.0f};		// v1
        short topologytrunk[] = {0,2,1, 	// v0, v1, v4
                2,3,1};						// v1, v5, v4

        ByteBuffer trunkVertexBB = ByteBuffer.allocateDirect(geometrytrunk.length * 4);
        trunkVertexBB.order(ByteOrder.nativeOrder());
        trunkVertexBuffer =trunkVertexBB.asFloatBuffer();
        trunkVertexBuffer.put(geometrytrunk);
        trunkVertexBuffer.position(0);

        ByteBuffer trunkColorBB = ByteBuffer.allocateDirect(colortrunk.length * 4);
        trunkColorBB.order(ByteOrder.nativeOrder());
        trunkColorBuffer = trunkColorBB.asFloatBuffer();
        trunkColorBuffer.put(colortrunk);
        trunkColorBuffer.position(0);

        ByteBuffer trunkTopologyBB = ByteBuffer.allocateDirect(topologytrunk.length * 2);
        trunkTopologyBB.order(ByteOrder.nativeOrder());
        trunkTopologyBuffer = trunkTopologyBB.asShortBuffer();
        trunkTopologyBuffer.put(topologytrunk);
        trunkTopologyBuffer.position(0);

        float geometryTop[] = {
                -1.0f, 2.0f, -1.0f,
                -1.0f, 2.0f, 1.0f,
                1.0f, 2.0f, -1.0f,
                1.0f, 2.0f, 1.0f };
        float colorTop[] = {
                r-0.1f, g, b, 0.0f,
                r-0.1f, g, b, 0.0f,
                r-0.1f, g, b, 0.0f,
                r-0.1f, g, b, 0.0f};
        short topologyTop[] = {0,1,2,
                2,1,3};

        ByteBuffer topVertexBB = ByteBuffer.allocateDirect(geometryTop.length * 4);
        topVertexBB.order(ByteOrder.nativeOrder());
        topVertexBuffer = topVertexBB.asFloatBuffer();
        topVertexBuffer.put(geometryTop);
        topVertexBuffer.position(0);

        ByteBuffer topColorBB = ByteBuffer.allocateDirect(colorTop.length * 4);
        topColorBB.order(ByteOrder.nativeOrder());
        topColorBuffer = topColorBB.asFloatBuffer();
        topColorBuffer.put(colorTop);
        topColorBuffer.position(0);

        ByteBuffer topTopologyBB = ByteBuffer.allocateDirect(topologyTop.length * 2);
        topTopologyBB.order(ByteOrder.nativeOrder());
        topTopologyBuffer = topTopologyBB.asShortBuffer();
        topTopologyBuffer.put(topologyTop);
        topTopologyBuffer.position(0);


        float geometryBackglassblack[] = {
                -0.95f,1.95f,-1.25f,
                -1.45f,0.85f,-2.25f,
                0.95f,1.95f,-1.25f,
                1.45f,0.85f,-2.25f};
        float colorBackglassblack[] = {
                0.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 0.0f, 0.0f};
        short topologyBackglassblack[] = {1,2,3,
                0,2,1};

        ByteBuffer backglassblackBB = ByteBuffer.allocateDirect(geometryBackglassblack.length * 4);
        backglassblackBB.order(ByteOrder.nativeOrder());
        backglassblackVertexBuffer = backglassblackBB.asFloatBuffer();
        backglassblackVertexBuffer.put(geometryBackglassblack);
        backglassblackVertexBuffer.position(0);

        ByteBuffer backglassblackColorBB = ByteBuffer.allocateDirect(colorBackglassblack.length * 4);
        backglassblackColorBB.order(ByteOrder.nativeOrder());
        backglassblackColorBuffer = backglassblackColorBB.asFloatBuffer();
        backglassblackColorBuffer.put(colorBackglassblack);
        backglassblackColorBuffer.position(0);

        ByteBuffer backglassblackTopologyBB = ByteBuffer.allocateDirect(topologyBackglassblack.length * 2);
        backglassblackTopologyBB.order(ByteOrder.nativeOrder());
        backglassblackTopoBuffer = backglassblackTopologyBB.asShortBuffer();
        backglassblackTopoBuffer.put(topologyBackglassblack);
        backglassblackTopoBuffer.position(0);




        float geometryfrontglassblack[] = {
                -0.95f,1.95f,1.0f,
                -1.45f,1.0f,2.0f,
                0.95f,1.95f,1.0f,
                1.45f,1.0f,2.0f,};
        float colorfrontglassblack[] = {
                0.0f, 0.0f, 0.f, 0.0f,
                0.0f, 0.0f, 0.f, 0.0f,
                0.0f, 0.0f, 0.f, 0.0f,
                0.0f, 0.0f, 0.f, 0.0f};
        short topologyfrontglassblack[] = {1,3,2,
                0,1,2 };

        ByteBuffer frontglassblackVertexBB = ByteBuffer.allocateDirect(geometryfrontglassblack.length * 4);
        frontglassblackVertexBB.order(ByteOrder.nativeOrder());
        frontglassblackVertexBuffer = frontglassblackVertexBB.asFloatBuffer();
        frontglassblackVertexBuffer.put(geometryfrontglassblack);
        frontglassblackVertexBuffer.position(0);

        ByteBuffer frontglassblackColorBB = ByteBuffer.allocateDirect(colorfrontglassblack.length * 4);
        frontglassblackColorBB.order(ByteOrder.nativeOrder());
        frontglassblackColorBuffer = frontglassblackColorBB.asFloatBuffer();
        frontglassblackColorBuffer.put(colorfrontglassblack);
        frontglassblackColorBuffer.position(0);

        ByteBuffer frontglassblackTopologyBB = ByteBuffer.allocateDirect(topologyfrontglassblack.length * 2);
        frontglassblackTopologyBB.order(ByteOrder.nativeOrder());
        frontglassblackTopologyBuffer = frontglassblackTopologyBB.asShortBuffer();
        frontglassblackTopologyBuffer.put(topologyfrontglassblack);
        frontglassblackTopologyBuffer.position(0);


        float geometryhood[] = {
                -1.5f,1.0f,2.0f,
                -1.5f, 1.0f, 5.0f,
                1.5f,1.0f,2.0f,
                1.5f, 1.0f, 5.0f
        };
        float colorhood[] = {
                r, g, b, 0.0f,
                r, g, b, 0.0f,
                r, g, b, 0.0f,
                r, g, b, 0.0f};		// v1
        short topologyhood[] = {0,1,2, 	// v0, v1, v4
                2,1,3};						// v1, v5, v4

        ByteBuffer hoodVertexBB = ByteBuffer.allocateDirect(geometryhood.length * 4);
        hoodVertexBB.order(ByteOrder.nativeOrder());
        hoodVertexBuffer =hoodVertexBB.asFloatBuffer();
        hoodVertexBuffer.put(geometryhood);
        hoodVertexBuffer.position(0);

        ByteBuffer hoodColorBB = ByteBuffer.allocateDirect(colorhood.length * 4);
        hoodColorBB.order(ByteOrder.nativeOrder());
        hoodColorBuffer = hoodColorBB.asFloatBuffer();
        hoodColorBuffer.put(colorhood);
        hoodColorBuffer.position(0);

        ByteBuffer hoodTopologyBB = ByteBuffer.allocateDirect(topologyhood.length * 2);
        hoodTopologyBB.order(ByteOrder.nativeOrder());
        hoodTopologyBuffer = hoodTopologyBB.asShortBuffer();
        hoodTopologyBuffer.put(topologyhood);
        hoodTopologyBuffer.position(0);


        float geometryrightglassblack[] = {
                1.1f,1.95f,1.0f,
                1.55f,1.2f,2.0f,				// v5
                1.1f,1.95f,-1.0f,
                1.55f,1.2f,-1.8f }; 			// v1
        float colorrightglassblack[] = {
                0.0f, 0.0f, 0.f, 0.0f,
                0.0f, 0.0f, 0.f, 0.0f,
                0.0f, 0.0f, 0.f, 0.0f,
                0.0f, 0.0f, 0.f, 0.0f};	// v1
        short topologyrightglassblack[] = {2, 0, 3, 	// v0, v1, v4
                3, 0, 1};						// v1, v5, v4

        ByteBuffer rightglassblackVertexBB = ByteBuffer.allocateDirect(geometryrightglassblack.length * 4);
        rightglassblackVertexBB.order(ByteOrder.nativeOrder());
        rightglassblackVertexBuffer = rightglassblackVertexBB.asFloatBuffer();
        rightglassblackVertexBuffer.put(geometryrightglassblack);
        rightglassblackVertexBuffer.position(0);

        ByteBuffer rightglassblackColorBB = ByteBuffer.allocateDirect(colorrightglassblack.length * 4);
        rightglassblackColorBB.order(ByteOrder.nativeOrder());
        rightglassblackColorBuffer = rightglassblackColorBB.asFloatBuffer();
        rightglassblackColorBuffer.put(colorrightglassblack);
        rightglassblackColorBuffer.position(0);

        ByteBuffer rightglassblackTopologyBB = ByteBuffer.allocateDirect(topologyrightglassblack.length * 2);
        rightglassblackTopologyBB.order(ByteOrder.nativeOrder());
        rightglassblackTopologyBuffer = rightglassblackTopologyBB.asShortBuffer();
        rightglassblackTopologyBuffer.put(topologyrightglassblack);
        rightglassblackTopologyBuffer.position(0);


        float geometryleftglassblack[] = {



                -1.1f,1.95f,1.0f,
                -1.55f,1.2f,2.0f,
               -1.1f,1.95f,-1.0f,
                -1.55f,1.2f,-1.8f };
        float colorleftglassblack[] = {
                0.0f, 0.0f, 0.f, 0.0f,
                0.0f, 0.0f, 0.f, 0.0f,
                0.0f, 0.0f, 0.f, 0.0f,
                0.0f, 0.0f, 0.f, 0.0f};	// v1
        short topologyleftglassblack[] = {2, 0, 3, 	// v0, v1, v4
                3, 0, 1};						// v1, v5, v4

        ByteBuffer leftglassblackVertexBB = ByteBuffer.allocateDirect(geometryleftglassblack.length * 4);
        leftglassblackVertexBB.order(ByteOrder.nativeOrder());
        leftglassblackVertexBuffer = leftglassblackVertexBB.asFloatBuffer();
        leftglassblackVertexBuffer.put(geometryleftglassblack);
        leftglassblackVertexBuffer.position(0);

        ByteBuffer leftglassblackColorBB = ByteBuffer.allocateDirect(colorleftglassblack.length * 4);
        leftglassblackColorBB.order(ByteOrder.nativeOrder());
        leftglassblackColorBuffer = leftglassblackColorBB.asFloatBuffer();
        leftglassblackColorBuffer.put(colorleftglassblack);
        leftglassblackColorBuffer.position(0);

        ByteBuffer leftglassblackTopologyBB = ByteBuffer.allocateDirect(topologyleftglassblack.length * 2);
        leftglassblackTopologyBB.order(ByteOrder.nativeOrder());
        leftglassblackTopologyBuffer = leftglassblackTopologyBB.asShortBuffer();
        leftglassblackTopologyBuffer.put(topologyleftglassblack);
        leftglassblackTopologyBuffer.position(0);

        float geometryfrontglassup[] = {
                -1.05f,1.95f,1.0f,
                -1.51f,1.05f,2.0f,				// v5
                -1.05f,1.95f,-1.0f,
                -1.51f,1.05f,-2.0f }; 			// v1
        float colorfrontglassup[] = {
                0.0f, 0.0f, 0.f, 0.0f,
                0.0f, 0.0f, 0.f, 0.0f,
                0.0f, 0.0f, 0.f, 0.0f,
                0.0f, 0.0f, 0.f, 0.0f};	// v1
        short topologyfrontglassup[] = {2, 0, 3, 	// v0, v1, v4
                3, 0, 1};						// v1, v5, v4

        ByteBuffer frontglassupVertexBB = ByteBuffer.allocateDirect(geometryfrontglassup.length * 4);
        frontglassupVertexBB.order(ByteOrder.nativeOrder());
        frontglassupVertexBuffer = frontglassupVertexBB.asFloatBuffer();
        frontglassupVertexBuffer.put(geometryfrontglassup);
        frontglassupVertexBuffer.position(0);

        ByteBuffer frontglassupColorBB = ByteBuffer.allocateDirect(colorfrontglassup.length * 4);
        frontglassupColorBB.order(ByteOrder.nativeOrder());
        frontglassupColorBuffer = frontglassupColorBB.asFloatBuffer();
        frontglassupColorBuffer.put(colorfrontglassup);
        frontglassupColorBuffer.position(0);

        ByteBuffer frontglassupTopologyBB = ByteBuffer.allocateDirect(topologyfrontglassup.length * 2);
        frontglassupTopologyBB.order(ByteOrder.nativeOrder());
        frontglassupTopologyBuffer = frontglassupTopologyBB.asShortBuffer();
        frontglassupTopologyBuffer.put(topologyfrontglassup);
        frontglassupTopologyBuffer.position(0);


        float geometryBacklightLA[] = {
                -1.5f, 1.0f, 5.3f,
                -1.5f, 0.5f, 5.3f,
                -1.35f, 1.0f, 5.3f,
                -1.35f, 0.5f, 5.3f};
        float colorBacklightLA[] = {
                1.0f, 1.f, 0.0f, 0.0f,
                1.0f, 1.f, 0.0f, 0.0f,
                1.0f, 1.f, 0.0f, 0.0f,
                1.0f, 1.f, 0.0f, 0.0f};
        short topologyBacklightLA[] = {0,1,2,
                1,3,2};

        ByteBuffer backlightLAVertexBB = ByteBuffer.allocateDirect(geometryBacklightLA.length * 4);
        backlightLAVertexBB.order(ByteOrder.nativeOrder());
        backlightLAvertexBuffer = backlightLAVertexBB.asFloatBuffer();
        backlightLAvertexBuffer.put(geometryBacklightLA);
        backlightLAvertexBuffer.position(0);

        ByteBuffer backlightLAColorBB = ByteBuffer.allocateDirect(colorBacklightLA.length * 4);
        backlightLAColorBB.order(ByteOrder.nativeOrder());
        backlightLAcolorBuffer = backlightLAColorBB.asFloatBuffer();
        backlightLAcolorBuffer.put(colorBacklightLA);
        backlightLAcolorBuffer.position(0);

        ByteBuffer backlightLATopologyBB = ByteBuffer.allocateDirect(topologyBacklightLA.length * 2);
        backlightLATopologyBB.order(ByteOrder.nativeOrder());
        backlightLAtopoBuffer = backlightLATopologyBB.asShortBuffer();
        backlightLAtopoBuffer.put(topologyBacklightLA);
        backlightLAtopoBuffer.position(0);


        float geometryBacklightLB[] = {
                -1.35f, 1.0f, 5.3f,
                -1.35f, 0.5f, 5.3f,
                -0.8f, 1.0f, 5.3f,
                -0.8f, 0.5f, 5.3f};
        float colorBacklightLB[] = {
                1.0f, 1.f, 1.0f, 0.0f,
                1.0f, 1.f, 1.0f, 0.0f,
                1.0f, 1.f, 1.0f, 0.0f,
                1.0f, 1.f, 1.0f, 0.0f};
        short topologyBacklightLB[] = {0,1,2,
                1,3,2};

        ByteBuffer backlightLBVertexBB = ByteBuffer.allocateDirect(geometryBacklightLB.length * 4);
        backlightLBVertexBB.order(ByteOrder.nativeOrder());
        backlightLBvertexBuffer = backlightLBVertexBB.asFloatBuffer();
        backlightLBvertexBuffer.put(geometryBacklightLB);
        backlightLBvertexBuffer.position(0);

        ByteBuffer backlightLBColorBB = ByteBuffer.allocateDirect(colorBacklightLB.length * 4);
        backlightLBColorBB.order(ByteOrder.nativeOrder());
        backlightLBcolorBuffer = backlightLBColorBB.asFloatBuffer();
        backlightLBcolorBuffer.put(colorBacklightLB);
        backlightLBcolorBuffer.position(0);

        ByteBuffer backlightLBTopologyBB = ByteBuffer.allocateDirect(topologyBacklightLB.length * 2);
        backlightLBTopologyBB.order(ByteOrder.nativeOrder());
        backlightLBtopoBuffer = backlightLBTopologyBB.asShortBuffer();
        backlightLBtopoBuffer.put(topologyBacklightLB);
        backlightLBtopoBuffer.position(0);



        float geometryBacklightRA[] = {
                1.5f, 1.0f, 5.3f,
                1.5f, 0.5f, 5.3f,
                1.35f, 1.0f, 5.3f,
                1.35f, 0.5f, 5.3f};
        float colorBacklightRA[] = {
                1.0f, 1.f, 0.0f, 0.0f,
                1.0f, 1.f, 0.0f, 0.0f,
                1.0f, 1.f, 0.0f, 0.0f,
                1.0f, 1.f, 0.0f, 0.0f};
        short topologyBacklightRA[] = {0,2,1,
                1,2,3};

        ByteBuffer backlightRAVertexBB = ByteBuffer.allocateDirect(geometryBacklightRA.length * 4);
        backlightRAVertexBB.order(ByteOrder.nativeOrder());
        backlightRAvertexBuffer = backlightRAVertexBB.asFloatBuffer();
        backlightRAvertexBuffer.put(geometryBacklightRA);
        backlightRAvertexBuffer.position(0);

        ByteBuffer backlightRAColorBB = ByteBuffer.allocateDirect(colorBacklightRA.length * 4);
        backlightRAColorBB.order(ByteOrder.nativeOrder());
        backlightRAcolorBuffer = backlightRAColorBB.asFloatBuffer();
        backlightRAcolorBuffer.put(colorBacklightRA);
        backlightRAcolorBuffer.position(0);

        ByteBuffer backlightRATopologyBB = ByteBuffer.allocateDirect(topologyBacklightRA.length * 2);
        backlightRATopologyBB.order(ByteOrder.nativeOrder());
        backlightRAtopoBuffer = backlightRATopologyBB.asShortBuffer();
        backlightRAtopoBuffer.put(topologyBacklightRA);
        backlightRAtopoBuffer.position(0);


        float geometryBacklightRB[] = {
                1.35f, 1.0f, 5.3f,
                1.35f, 0.5f, 5.3f,
                0.8f, 1.0f, 5.3f,
                0.8f, 0.5f, 5.3f};
        float colorBacklightRB[] = {
                1.0f, 1.f, 1.0f, 0.0f,
                1.0f, 1.f, 1.0f, 0.0f,
                1.0f, 1.f, 1.0f, 0.0f,
                1.0f, 1.f, 1.0f, 0.0f};
        short topologyBacklightRB[] = {0,2,1,
                1,2,3};

        ByteBuffer backlightRBVertexBB = ByteBuffer.allocateDirect(geometryBacklightRB.length * 4);
        backlightRBVertexBB.order(ByteOrder.nativeOrder());
        backlightRBvertexBuffer = backlightRBVertexBB.asFloatBuffer();
        backlightRBvertexBuffer.put(geometryBacklightRB);
        backlightRBvertexBuffer.position(0);

        ByteBuffer backlightRBColorBB = ByteBuffer.allocateDirect(colorBacklightRB.length * 4);
        backlightRBColorBB.order(ByteOrder.nativeOrder());
        backlightRBcolorBuffer = backlightRBColorBB.asFloatBuffer();
        backlightRBcolorBuffer.put(colorBacklightRB);
        backlightRBcolorBuffer.position(0);

        ByteBuffer backlightRBTopologyBB = ByteBuffer.allocateDirect(topologyBacklightRB.length * 2);
        backlightRBTopologyBB.order(ByteOrder.nativeOrder());
        backlightRBtopoBuffer = backlightRBTopologyBB.asShortBuffer();
        backlightRBtopoBuffer.put(topologyBacklightRB);
        backlightRBtopoBuffer.position(0);
    }

    public void draw(GL10 gl) {

        gl.glPushMatrix();


            gl.glMultMatrixf(transformationMatrix, 0);






          /*  gl.glColor4f(1.0f, 0.0f, 0.0f, 0.0f);
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, bottomvertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, bottomcolorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, bottomtopoBuffer.limit(), GL10.GL_UNSIGNED_SHORT, bottomtopoBuffer);
            gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);


            gl.glColor4f(1.0f, 0.0f, 0.0f, 0.0f);
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, stossvertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, stosscolorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, stosstopoBuffer.limit(), GL10.GL_UNSIGNED_SHORT, stosstopoBuffer);
            gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);


            //Heck
            gl.glColor4f(1.0f, 0.0f, 0.0f, 0.0f);
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, backvertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, backcolorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, backtopoBuffer.limit(), GL10.GL_UNSIGNED_SHORT, backtopoBuffer);
            gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);


            //Left
            gl.glColor4f(1.0f, 0.0f, 0.0f, 0.0f);
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, leftvertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, leftcolorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, lefttopoBuffer.limit(), GL10.GL_UNSIGNED_SHORT, lefttopoBuffer);
            gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);


            //Right
            gl.glColor4f(1.0f, 0.0f, 0.0f, 0.0f);
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, topoBuffer.limit(), GL10.GL_UNSIGNED_SHORT, topoBuffer);
            gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);*/

            gl.glColor4f(1.0f, 0.0f, 0.0f, 0.0f);

            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY);


            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, frontglassblackVertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, frontglassblackColorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, frontglassblackTopologyBuffer.limit(), GL10.GL_UNSIGNED_SHORT, frontglassblackTopologyBuffer);


            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, bottomvertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, bottomcolorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, bottomtopoBuffer.limit(), GL10.GL_UNSIGNED_SHORT, bottomtopoBuffer);



            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, stossvertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, stosscolorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, stosstopoBuffer.limit(), GL10.GL_UNSIGNED_SHORT, stosstopoBuffer);

            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, backvertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, backcolorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, backtopoBuffer.limit(), GL10.GL_UNSIGNED_SHORT, backtopoBuffer);

            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, leftvertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, leftcolorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, lefttopoBuffer.limit(), GL10.GL_UNSIGNED_SHORT, lefttopoBuffer);

            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, rightvertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, rightcolorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, righttopoBuffer.limit(), GL10.GL_UNSIGNED_SHORT, righttopoBuffer);

            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, backglassVertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, backglassColorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, backglassTopoBuffer.limit(), GL10.GL_UNSIGNED_SHORT, backglassTopoBuffer);

            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, backglassblackVertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, backglassblackColorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, backglassblackTopoBuffer.limit(), GL10.GL_UNSIGNED_SHORT, backglassblackTopoBuffer);



           /* gl.glVertexPointer(3, GL10.GL_FLOAT, 0, frontglassVertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, frontglassColorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, frontglassTopologyBuffer.limit(), GL10.GL_UNSIGNED_SHORT, frontglassTopologyBuffer);*/




            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, leftglassVertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, leftglassColorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, leftglassTopologyBuffer.limit(), GL10.GL_UNSIGNED_SHORT, leftglassTopologyBuffer);

            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, rightglassVertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, rightglassColorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, rightglassTopologyBuffer.limit(), GL10.GL_UNSIGNED_SHORT, rightglassTopologyBuffer);

            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, trunkVertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, trunkColorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, trunkTopologyBuffer.limit(), GL10.GL_UNSIGNED_SHORT, trunkTopologyBuffer);

            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, topVertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, topColorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, topTopologyBuffer.limit(), GL10.GL_UNSIGNED_SHORT, topTopologyBuffer);


            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, hoodVertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, hoodColorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, hoodTopologyBuffer.limit(), GL10.GL_UNSIGNED_SHORT,hoodTopologyBuffer);


            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, rightglassblackVertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, rightglassblackColorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, rightglassblackTopologyBuffer.limit(), GL10.GL_UNSIGNED_SHORT, rightglassTopologyBuffer);


            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, leftglassblackVertexBuffer);
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, leftglassblackColorBuffer);
            gl.glDrawElements(GL10.GL_TRIANGLES, leftglassblackTopologyBuffer.limit(), GL10.GL_UNSIGNED_SHORT, leftglassTopologyBuffer);


        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, backlightLAvertexBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, backlightLAcolorBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, backlightLAtopoBuffer.limit(), GL10.GL_UNSIGNED_SHORT, backlightLAtopoBuffer);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, backlightLBvertexBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, backlightLBcolorBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, backlightLBtopoBuffer.limit(), GL10.GL_UNSIGNED_SHORT, backlightLBtopoBuffer);

        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, backlightRAvertexBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, backlightRAcolorBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, backlightRAtopoBuffer.limit(), GL10.GL_UNSIGNED_SHORT, backlightRAtopoBuffer);


        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, backlightRBvertexBuffer);
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, backlightRBcolorBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, backlightRBtopoBuffer.limit(), GL10.GL_UNSIGNED_SHORT, backlightRBtopoBuffer);

            gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        gl.glPopMatrix();
    }

    public void updatePosition(float fracSec){


            Matrix.translateM(transformationMatrix, 0, 0, 0, fracSec+ speed);
            zCoord += fracSec +speed;


    }

    public float getZ(){
        return zCoord;
    }


}

