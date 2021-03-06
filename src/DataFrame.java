import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class DataFrame {
	
	private String[] frameStructure;
	private ArrayList<DataBlock> dataBlocks = new ArrayList<DataBlock>();
	public int devc, dvid;
	public String dvnm;
	public float[] tmpc = new float[2];
	public int[] tick = new int[6];
	public int[] strm = new int[5];
	public int[] tsmp = new int[5];
	public String[] stnm = new String[5];
	public AccelerometerBlock accl = new AccelerometerBlock();
	public GyroBlock gyro = new GyroBlock();
	public GPSBlock gps = new GPSBlock();
	public SensorBlock sensorIso = new SensorBlock();
	public SensorBlock sensorExposure = new SensorBlock();
	
	
	@SuppressWarnings("unchecked")
	public DataFrame(String[] _frameStructure, ByteArrayInputStream byteReader) {
		
		frameStructure = _frameStructure;
		
		// read 122 dataBlocks
		for(int i = 0; i < frameStructure.length; i++) {
			dataBlocks.add(new DataBlock(byteReader));
		}
		if(frameStructure.length == 41) {
			devc = (int) dataBlocks.get(0).value;
			dvid = (int) dataBlocks.get(1).value;
			dvnm = (String) dataBlocks.get(2).value;
			tick[0] = (int) dataBlocks.get(3).value;
			strm[0] = (int) dataBlocks.get(4).value;
			tsmp[0] = (int) dataBlocks.get(5).value;
			tick[1] = (int) dataBlocks.get(6).value;
			stnm[0] = (String) dataBlocks.get(7).value;
			tmpc[0] = ((ArrayList<Float>) dataBlocks.get(8).value).get(0);
			
			accl.setStreamNm((String) dataBlocks.get(7).value);
			accl.setTmpc(((ArrayList<Float>) dataBlocks.get(8).value).get(0));
			accl.setSIUnit((String) dataBlocks.get(9).value);
			accl.setScale(((ArrayList<Short>) dataBlocks.get(10).value).get(0));
			accl.setRawData(((ArrayList<Short>) dataBlocks.get(11).value));
			
			strm[1] = (int) dataBlocks.get(12).value;
			tsmp[1] = (int) dataBlocks.get(13).value;
			tick[2] = (int) dataBlocks.get(14).value;
			stnm[1] = (String) dataBlocks.get(15).value;
			tmpc[1] = ((ArrayList<Float>) dataBlocks.get(16).value).get(0);
			
			gyro.setStreamNm((String) dataBlocks.get(15).value);
			gyro.setTmpc(((ArrayList<Float>) dataBlocks.get(16).value).get(0));
			gyro.setSIUnit((String) dataBlocks.get(17).value);
			gyro.setScale(((ArrayList<Short>) dataBlocks.get(18).value).get(0));
			gyro.setRawData(((ArrayList<Short>) dataBlocks.get(19).value));
			
			strm[2] = (int) dataBlocks.get(20).value;
			tsmp[2] = (int) dataBlocks.get(21).value;
			tick[3] = (int) dataBlocks.get(22).value;
			
			gps.setGpsf((int) dataBlocks.get(23).value);
			gps.setGpsu((String) dataBlocks.get(24).value);
			gps.setGpsp((short) dataBlocks.get(25).value);
			gps.setStreamNm((String) dataBlocks.get(26).value);
			gps.setUnits((String) dataBlocks.get(27).value);
			gps.setScales((ArrayList<Integer>) dataBlocks.get(28).value);
			gps.setRawData((ArrayList<Integer>) dataBlocks.get(29).value);
			
			strm[3] = (int) dataBlocks.get(30).value;
			tsmp[3] = (int) dataBlocks.get(31).value;
			tick[4] = (int) dataBlocks.get(32).value;
			
			sensorIso.setStreamNm((String) dataBlocks.get(33).value);
			sensorIso.setRawData((ArrayList<Float>) dataBlocks.get(34).value);
			
			strm[4] = (int) dataBlocks.get(35).value;
			tsmp[4] = (int) dataBlocks.get(36).value;
			tick[5] = (int) dataBlocks.get(37).value;
			
			sensorExposure.setStreamNm((String) dataBlocks.get(38).value);
			sensorExposure.setSIUnit((String) dataBlocks.get(39).value);
			sensorExposure.setRawData((ArrayList<Float>) dataBlocks.get(40).value);
		}
		
		else if(frameStructure.length == 23) {
			devc = (int) dataBlocks.get(0).value;
			dvid = (int) dataBlocks.get(1).value;
			dvnm = (String) dataBlocks.get(2).value;
			strm[0] = (int) dataBlocks.get(3).value;
			tsmp[0] = (int) dataBlocks.get(4).value;
			
			accl.setStreamNm("Accelerometer (up/down, right/left, forward/back) ");
			accl.setSIUnit((String) dataBlocks.get(5).value);
			accl.setScale(((ArrayList<Short>) dataBlocks.get(6).value).get(0));

			accl.setTmpc(((ArrayList<Float>) dataBlocks.get(7).value).get(0));
			tmpc[0] = ((ArrayList<Float>) dataBlocks.get(7).value).get(0);
			accl.setRawData(((ArrayList<Short>) dataBlocks.get(8).value));
			
			strm[1] = (int) dataBlocks.get(9).value;
			tsmp[1] = (int) dataBlocks.get(10).value;
			
			gyro.setStreamNm("Gyroscope (z,x,y) ");
			gyro.setSIUnit((String) dataBlocks.get(11).value);
			gyro.setScale(((ArrayList<Short>) dataBlocks.get(12).value).get(0));
			gyro.setTmpc(((ArrayList<Float>) dataBlocks.get(13).value).get(0));
			tmpc[0] = ((ArrayList<Float>) dataBlocks.get(13).value).get(0);			
			gyro.setRawData(((ArrayList<Short>) dataBlocks.get(14).value));
			
			strm[2] = (int) dataBlocks.get(15).value;
			tsmp[2] = (int) dataBlocks.get(16).value;
			
			gps.setGpsf((int) dataBlocks.get(17).value);
			gps.setGpsu((String) dataBlocks.get(18).value);
			gps.setGpsp((short) dataBlocks.get(19).value);
			gps.setStreamNm("GPS (Lat., Long., Alt., 2D speed, 3D speed) ");
			gps.setUnits((String) dataBlocks.get(20).value);
			gps.setScales((ArrayList<Integer>) dataBlocks.get(21).value);
			gps.setRawData((ArrayList<Integer>) dataBlocks.get(22).value);

		} else {
			System.err.println("Unsupported Frame Structure: " + Arrays.toString(frameStructure));
			return;
		};

		
		// clear
		dataBlocks.clear();
	}

	public Calendar getGpsDate() {
		return gps.getReferenceDate();
	}

	@Override
	public String toString() {
		return "DataFrame [dataBlocks=" + dataBlocks + ", devc=" + devc + ", dvid=" + dvid + ", dvnm=" + dvnm
				+ ", tmpc=" + Arrays.toString(tmpc) + ", tick=" + Arrays.toString(tick) + ", strm="
				+ Arrays.toString(strm) + ", tsmp=" + Arrays.toString(tsmp) + ", stnm=" + Arrays.toString(stnm)
				+ ", accl=" + accl + ", gyro=" + gyro + ", gps=" + gps + ", sensorIso=" + sensorIso
				+ ", sensorExposure=" + sensorExposure + "]";
	}





	
	
}
