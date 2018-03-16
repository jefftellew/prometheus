package game;

import java.util.List;
import java.util.ArrayList;

public class WaveMap
{
    private final List<Enemy> wave1;
    private final List<Enemy> wave2;
    private final List<Enemy> wave3;
    private final List<Enemy> wave4;
    private final List<Enemy> wave5;
    private final List<Enemy> wave6;
    private final List<Enemy> wave7;
    private final List<Enemy> wave8;
    private final List<Enemy> wave9;
    private final List<Enemy> wave10;

    public WaveMap()
    {
        wave1 = new ArrayList<Enemy>();
        wave2 = new ArrayList<Enemy>();
        wave3 = new ArrayList<Enemy>();
        wave4 = new ArrayList<Enemy>();
        wave5 = new ArrayList<Enemy>();
        wave6 = new ArrayList<Enemy>();
        wave7 = new ArrayList<Enemy>();
        wave8 = new ArrayList<Enemy>();
        wave9 = new ArrayList<Enemy>();
        wave10 = new ArrayList<Enemy>();

        createWave1();
        createWave2();
        createWave3();
        createWave4();
        createWave5();
        createWave6();
        createWave7();
        createWave8();
        createWave9();
        createWave10();
    }

    private void createWave1()
    {   	
        wave1.add(new BasicEnemy());
        wave1.add(new BasicEnemy());
        wave1.add(new BasicEnemy());
        wave1.add(new BasicEnemy());
        wave1.add(new BasicEnemy());
        
        wave1.add(new BasicEnemy());
        wave1.add(new BasicEnemy());
        wave1.add(new BasicEnemy());
        wave1.add(new BasicEnemy());
        wave1.add(new BasicEnemy());
    }

    private void createWave2()
    {
        wave2.add(new BasicEnemy());
        wave2.add(new BasicEnemy());
        wave2.add(new BasicEnemy());
        wave2.add(new BasicEnemy());
        wave2.add(new BasicEnemy());

        wave2.add(new BasicEnemy());
        wave2.add(new BasicEnemy());
        wave2.add(new BasicEnemy());
        wave2.add(new BasicEnemy());
        wave2.add(new BasicEnemy());
        
        wave2.add(new BasicEnemy());
        wave2.add(new BasicEnemy());
        wave2.add(new BasicEnemy());
        wave2.add(new BasicEnemy());
        wave2.add(new BasicEnemy());
    }

    private void createWave3()
    {
        wave3.add(new BasicEnemy());
        wave3.add(new BasicEnemy());
        wave3.add(new BasicEnemy());
        wave3.add(new BasicEnemy());
        wave3.add(new BasicEnemy());
        
        wave3.add(new BasicEnemy());
        wave3.add(new BasicEnemy());
        wave3.add(new BigEnemy());
        wave3.add(new BigEnemy());
        wave3.add(new BasicEnemy());

        wave3.add(new BasicEnemy());
        wave3.add(new BasicEnemy());
        wave3.add(new BigEnemy());
    }

    private void createWave4()
    {
        wave4.add(new BasicEnemy());
        wave4.add(new FastEnemy());
        wave4.add(new BasicEnemy());
        wave4.add(new FastEnemy());
        wave4.add(new BasicEnemy());

        wave4.add(new BasicEnemy());
        wave4.add(new BigEnemy());
        wave4.add(new BasicEnemy());
        wave4.add(new FastEnemy());
        wave4.add(new BasicEnemy());
        
        wave4.add(new BasicEnemy());
        wave4.add(new BigEnemy());
        wave4.add(new BasicEnemy());
        wave4.add(new FastEnemy());
        wave4.add(new BasicEnemy());
        
        wave4.add(new BasicEnemy());
        wave4.add(new BigEnemy());
        wave4.add(new BasicEnemy());
        wave4.add(new FastEnemy());
        wave4.add(new BasicEnemy());
    }

    private void createWave5()
    {
        wave5.add(new BasicEnemy());
        wave5.add(new BigEnemy());
        wave5.add(new BigEnemy());
        wave5.add(new FastEnemy());
        wave5.add(new FastEnemy());

        wave5.add(new BasicEnemy());
        wave5.add(new BigEnemy());
        wave5.add(new BasicEnemy());
        wave5.add(new FastEnemy());
        wave5.add(new BasicEnemy());
        
        wave5.add(new BasicEnemy());
        wave5.add(new BigEnemy());
        wave5.add(new BigEnemy());
        wave5.add(new FastEnemy());
        wave5.add(new FastEnemy());

        wave5.add(new BasicEnemy());
        wave5.add(new BigEnemy());
        wave5.add(new BasicEnemy());
        wave5.add(new FastEnemy());
        wave5.add(new BasicEnemy());
    }

    private void createWave6()
    {
        wave6.add(new BasicEnemy());
        wave6.add(new BasicEnemy());
        wave6.add(new BigEnemy());
        wave6.add(new BigEnemy());
        wave6.add(new BasicEnemy());

        wave6.add(new BigEnemy());
        wave6.add(new BasicEnemy());
        wave6.add(new BigEnemy());
        wave6.add(new BasicEnemy());
        wave6.add(new FastEnemy());

        wave6.add(new BasicEnemy());
        wave6.add(new FastEnemy());
        wave6.add(new BigEnemy());
        wave6.add(new FastEnemy());
        wave6.add(new HugeEnemy());
        
        wave6.add(new BasicEnemy());
        wave6.add(new BasicEnemy());
        wave6.add(new HugeEnemy());
        wave6.add(new BigEnemy());
        wave6.add(new BasicEnemy());

        wave6.add(new BigEnemy());
        wave6.add(new HugeEnemy());
        wave6.add(new BigEnemy());
        wave6.add(new HugeEnemy());
        wave6.add(new FastEnemy());

        wave6.add(new BasicEnemy());
        wave6.add(new FastEnemy());
        wave6.add(new BigEnemy());
    }

    private void createWave7()
    {
        wave7.add(new BigEnemy());
        wave7.add(new BigEnemy());
        wave7.add(new BigEnemy());
        wave7.add(new BigEnemy());
        wave7.add(new BigEnemy());
        
        wave7.add(new BigEnemy());
        wave7.add(new FastEnemy());
        wave7.add(new BigEnemy());
        wave7.add(new FastEnemy());
        wave7.add(new BigEnemy());
        
        wave7.add(new BigEnemy());
        wave7.add(new HugeEnemy());
        wave7.add(new BigEnemy());
        wave7.add(new HugeEnemy());
        wave7.add(new BigEnemy());
        
        wave7.add(new FastEnemy());
        wave7.add(new FastEnemy());
        wave7.add(new FastEnemy());
        wave7.add(new FastEnemy());
        wave7.add(new FastEnemy());
        
        wave7.add(new FastEnemy());
        wave7.add(new HugeEnemy());
        wave7.add(new FastEnemy());
        wave7.add(new HugeEnemy());
        wave7.add(new FastEnemy());
    }
    private void createWave8()
    {
        wave8.add(new HugeEnemy());
        wave8.add(new BigEnemy());
        wave8.add(new HugeEnemy());
        wave8.add(new BigEnemy());
        wave8.add(new HugeEnemy());
        
        wave8.add(new HugeEnemy());
        wave8.add(new BigEnemy());
        wave8.add(new HugeEnemy());
        wave8.add(new BigEnemy());
        wave8.add(new HugeEnemy());
        
        wave8.add(new HugeEnemy());
        wave8.add(new HugeEnemy());
        wave8.add(new HugeEnemy());
        wave8.add(new HugeEnemy());
        wave8.add(new HugeEnemy());
        
        wave8.add(new FastEnemy());
        wave8.add(new FastEnemy());
        wave8.add(new FastEnemy());
        wave8.add(new FastEnemy());
        wave8.add(new FastEnemy());
        
        wave8.add(new HugeEnemy());
        wave8.add(new BigEnemy());
        wave8.add(new HugeEnemy());
        wave8.add(new BigEnemy());
        wave8.add(new HugeEnemy());
        
        wave8.add(new HugeEnemy());
        wave8.add(new BigEnemy());
        wave8.add(new HugeEnemy());
        wave8.add(new BigEnemy());
        wave8.add(new HugeEnemy());
    }
    private void createWave9()
    {
        wave9.add(new BigEnemy());
        wave9.add(new BigFastEnemy());
        wave9.add(new BigEnemy());
        wave9.add(new BigFastEnemy());
        wave9.add(new BigEnemy());
        
        wave9.add(new BigFastEnemy());
        wave9.add(new BigFastEnemy());
        wave9.add(new BigFastEnemy());
        wave9.add(new BigFastEnemy());
        wave9.add(new BigFastEnemy());
        
        wave9.add(new HugeEnemy());
        wave9.add(new BigEnemy());
        wave9.add(new HugeEnemy());
        wave9.add(new BigEnemy());
        wave9.add(new HugeEnemy());
        
        wave9.add(new HugeEnemy());
        wave9.add(new HugeEnemy());
        wave9.add(new HugeEnemy());
        wave9.add(new HugeEnemy());
        wave9.add(new BigFastEnemy());
        
        wave9.add(new HugeEnemy());
        wave9.add(new HugeEnemy());
        wave9.add(new HugeEnemy());
    }
    private void createWave10()
    {
    	wave10.add(new BigEnemy());
    	wave10.add(new BigEnemy());
    	wave10.add(new BigEnemy());
    	wave10.add(new BigEnemy());
    	wave10.add(new BigEnemy());
    	
    	wave10.add(new HugeEnemy());
    	wave10.add(new BigEnemy());
    	wave10.add(new HugeEnemy());
    	wave10.add(new BigEnemy());
    	wave10.add(new HugeEnemy());
    	
    	wave10.add(new MiniBossEnemy());
    	wave10.add(new BigFastEnemy());
    	wave10.add(new BigEnemy());
    	wave10.add(new BigFastEnemy());
    	wave10.add(new BigEnemy());
    	
    	wave10.add(new HugeEnemy());
    	wave10.add(new BigEnemy());
    	wave10.add(new HugeEnemy());
    	wave10.add(new BigEnemy());
    	wave10.add(new HugeEnemy());
    	
    	wave10.add(new MiniBossEnemy());
    	wave10.add(new HugeEnemy());
    	wave10.add(new HugeEnemy());
    	wave10.add(new HugeEnemy());
        wave10.add(new BossEnemy());
    }

    public List<Enemy> getWave(int wave)
    {
        switch(wave)
        {
            case 1: return wave1;
            case 2: return wave2;
            case 3: return wave3;
            case 4: return wave4;
            case 5: return wave5;
            case 6: return wave6;
            case 7: return wave7;
            case 8: return wave8;
            case 9: return wave9;
            case 10: return wave10;
            default: return null;
        }
    }
}
