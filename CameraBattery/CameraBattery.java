package hw1;

/**
 * Model of a Removable and Rechargeable Camera Battery
 * @author yavuz
 *
 */

public class CameraBattery {
	/**
	 * Camera Settings Constant
	 */
	public static final int NUM_CHARGER_SETTINGS = 4;
	
	/**
	 * Charging Rate of Change Constant
	 */
	public static final double CHARGE_RATE = 2.0;
	
	/**
	 * Initial Camera Consumption Power
	 */
	public static final double DEFAULT_CAMERA_POWER_CONSUMPTION = 1.0;
	
	/**
	 * CameraCharge - Stores the Total Charge that are in the Camera
	 */
	private double CameraCharge;
	
	/**
	 * BatteryCharge - Stores the Total Charge that are in the Battery
	 */
	private double BatteryCharge;
	
	/**
	 * BatteryCapacity - Maximum Capacity of the Battery
	 */
	private double BatteryCapacity;
	
	/**
	 * AmountChargedCamera - How Much Did the Battery Charged the Camera at a Step 
	 */
	private double AmountChargedCamera;
	
	/**
	 * AmountDrained - How Much Did the Camera or Battery Lose Charge at a Step
	 */
	private double AmountDrained;
	
	/**
	 * AmountChragedBattery - How Much Did the External Charger Charged the Battery at a Step
	 */
	private double AmountChragedBattery;
	
	/**
	 * ConnectCamera - Checks if the Battery is Connected to the Camera
	 * ConnectCamera = 0 When Disconnected, ConnectCamera = 1 When Connected
	 */
	private double ConnectCamera;
	
	/**
	 * ConnectCharger - Checks if the Battery is Connected to the ExternalCharger
	 * ConnectCharger = 0 When Disconnected, ConnectCharger = 1 When Connected
	 */
	private double ConnectCharger;
	
	/**
	 * ChargerSetting - External Charger's Setting
	 */
	private int ChargerSetting;
	
	/**
	 * CamCons - Camera Consumption Variable if the User Choses to Set One
	 */
	private double CamCons;
	
	/**
	 * TotalDrain - Addition of Each Draining Step
	 */
	private double TotalDrain;
	
	/**
	 * Limits the Startin Battery Charge to the Capacity
	 * Sets the ChargerSetting
	 * Since There are 4 Settings I used %4
	 * Sets the Camera's Consumption to the Default
	 * Gets the Battery Capacity From the Parameter
	 * There Should be no Connection to the Battery
	 * So Initialized the Connection Variables to 0
	 * @param batteryStartingCharge - Initial Charge of the Battery
	 * @param batteryCapacity - Maximum Capacity of the Battery
	 */
	 public CameraBattery(double batteryStartingCharge, double batteryCapacity) {
		 BatteryCharge = Math.min(batteryStartingCharge, batteryCapacity);
		 ChargerSetting = NUM_CHARGER_SETTINGS % 4;
		 CamCons = DEFAULT_CAMERA_POWER_CONSUMPTION;
		 BatteryCapacity = batteryCapacity;
		 ConnectCharger = 0;
		 ConnectCamera = 0;
	 }
	 
	 /**
	  * Goes to the Next Charger Setting
	  * The Setting Goes Back to 0 if it Becomes 4 Again
	  */
	 public void buttonPress() {
		 ChargerSetting += 1;
		 ChargerSetting = ChargerSetting % 4;
	 }
	 
	 /**
	  * Calculates the Increase of the Charge in the Camera
	  * Limits it to the Empty Space in the Camera
	  * Adds this Amount to the Batteries
	  * Limits the Batteries to the Maximum Capacity
	  * @param minutes - The Time Variable
	  * @return - How Much Did the Battery Charged the Camera at a Step
	  */
	 public double cameraCharge(double minutes) {
		 AmountChargedCamera = minutes * CHARGE_RATE * ConnectCamera;
		 AmountChargedCamera = Math.min(AmountChargedCamera, BatteryCapacity - CameraCharge);
		 CameraCharge += AmountChargedCamera;
		 BatteryCharge += AmountChargedCamera;
		 CameraCharge = Math.min(CameraCharge, BatteryCapacity);
		 BatteryCharge = Math.min(BatteryCharge, BatteryCapacity);
		 return AmountChargedCamera;
	 }
	 
	 /**
	  * Calculates the Decrease in the Batteries
	  * Limits it to the Charge Left in the Camera
	  * Decrease This Amount From the Batteries
	  * Adds The Amount to the Total Amount of Drain
	  * Sets the Lower Limit for the Batteries to 0;
	  * @param minutes - The Time Variable
	  * @return - How Much Did the Camera or Battery Lose Charge at a Step
	  */
	 public double drain(double minutes) {
		AmountDrained = minutes * CamCons * ConnectCamera;
		AmountDrained = Math.min(AmountDrained, CameraCharge);
		CameraCharge -= AmountDrained;
		BatteryCharge -= AmountDrained;
		TotalDrain += AmountDrained;
		CameraCharge = Math.max(CameraCharge, 0);
		BatteryCharge = Math.max(BatteryCharge, 0);
		return AmountDrained;
	 }
	 
	 /**
	  * Calculates the Increase in the Battery's Charge
	  * Limits it to the Empty Space in the Battery
	  * Adds this Amount to the Battery
	  * Limits it to the Maximum Capacity
	  * @param minutes - The Time Variable
	  * @return - How Much Did the External Charger Charged the Battery at a Step
	  */
	 public double externalCharge(double minutes) {
		 AmountChragedBattery = minutes * ChargerSetting * CHARGE_RATE * ConnectCharger;
		 AmountChragedBattery = Math.min(AmountChragedBattery, BatteryCapacity - BatteryCharge);
		 BatteryCharge += AmountChragedBattery;
		 BatteryCharge = Math.min(BatteryCharge, BatteryCapacity);
		 return AmountChragedBattery;
	 }
	 
	 /**
	  * Resets the  Total Battery Drain Count to 0
	  */
	 public void resetBatteryMonitor() {
		 TotalDrain = 0;
	 }
	 
	 /**
	  * Gets the Maximum Capacity of the Battery
	  * @return - Maximum Capacity of the Battery
	  */
	 public double getBatteryCapacity() {
		
		 return BatteryCapacity;
	 }
	 
	 /**
	  * Gets Battery's Charge
	  * @return - Total Charge that are in the Battery
	  */
	 public double getBatteryCharge() {
		 
		 return BatteryCharge;
	 }
	 
	 /**
	  * Gets Camera's Charge
	  * @return - Total Charge that are in the Camera
	  */
	 public double getCameraCharge() {
		 
		 return CameraCharge;
	 }
	 
	 /**
	  * Gets the Value of the Camera Power Consumption
	  * @return - Camera Consumption
	  */
	 public double getCameraPowerConsumption() {
		 
		 return CamCons;
	 }
	 
	 /**
	  * Gets External Charger's Setting Number
	  * @return - External Charger's Setting
	  */
	 public int getChargerSetting() {
		 
		 return ChargerSetting;
	 }
	 
	 /**
	  * Gets the Amount of the Total Drain
	  * @return - Addition of Each Draining Step
	  */
	 public double getTotalDrain() {
		 
		 return TotalDrain;
	 }
	 
	 /**
	  * Connects the Battery to the Charger
	  * Disconnects the Battery From the Camera
	  */
	 public void moveBatteryExternal() {
		 ConnectCamera = 0;
		 ConnectCharger = 1;
	 }
	 
	 /**
	  * Disconnects the Battery From the Charger
	  * Connects the Battery to the Camera
	  * Adds the Charge of the Battery to the Camera's Battery
	  */
	 public void moveBatteryCamera() {
		 ConnectCamera = 1;
		 ConnectCharger = 0;
		 CameraCharge += BatteryCharge;
	 }
	 
	 /**
	  * Cuts All the Connection by Initializing the Connection Variables to 0
	  * The Battery Will not be Attached to the Camera Anymore
	  * So I Decreased the Battery's Charge From the Camera's Charge
	  */
	 public void removeBattery() {
		 ConnectCharger = 0;
		 ConnectCamera = 0;
		 CameraCharge -= BatteryCharge;
	 }
	 
	 /**
	  * User Sets the Camera's Consumption Power Rate
	  * @param cameraPowerConsumption - Camera Consumption That the User Sets
	  */
	 public void setCameraPowerConsumption(double cameraPowerConsumption) {
		 CamCons = cameraPowerConsumption;
	 }
}
