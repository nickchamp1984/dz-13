package cc.robotdreams.lessons.lesson19.solid;

public class D_InterfaceSegregation
{
    public interface ICar
    {
        void run(int pointB);
    }

    public interface IManualTransmissionCar
    {
        void setTransmission(int num);
        int transmissionGearsCount();
    }

    public interface IFuelEngineCar
    {
        void startEngine();
    }

    static public class ElectricCar implements ICar
    {
        @Override
        public void run(int pointB) {
            // Start driving
        }
    }

    static public class GasolineAutomaticTransmissionCar implements ICar, IFuelEngineCar
    {
        @Override
        public void startEngine() {
            // Start engine
        }

        @Override
        public void run(int pointB) {
            // Start driving
        }
    }

    static public class GasolineManualTransmissionCar implements ICar, IFuelEngineCar, IManualTransmissionCar
    {
        @Override
        public void run(int pointB) {
            // Start driving
        }

        @Override
        public void setTransmission(int num) {
            // Set transmission
        }

        @Override
        public int transmissionGearsCount() {
            return 5;
        }

        @Override
        public void startEngine() {
            // Start engine
        }
    }
}
