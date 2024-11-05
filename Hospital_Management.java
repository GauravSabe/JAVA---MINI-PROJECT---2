import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Patient 
{
    private static int idCounter = 0;
    private String patientId;
    private String name;
    private int age;
    private String gender;
    private String address;
    private String phoneNumber;
    private String medicalHistory;

    public Patient() 
    {
        this.patientId = "P" + (++idCounter);
    }

    // Getters and Setters
    public String getPatientId() 
    {
        return patientId;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public int getAge() 
    {
        return age;
    }

    public void setAge(int age) 
    {
        this.age = age;
    }

    public String getGender() 
    {
        return gender;
    }

    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getMedicalHistory() 
    {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) 
    {
        this.medicalHistory = medicalHistory;
    }

    public void registerPatient() 
    {
        System.out.println("Patient registered: " + this.name);
    }

    public void updatePatientDetails() 
    {
        System.out.println("Patient details updated: " + this.name);
    }

    public void viewPatientDetails() 
    {
        System.out.println("Patient ID: " + patientId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Medical History: " + medicalHistory);
    }

    public void bookAppointment(Hospital hospital, Doctor doctor, String date) 
    {
        Appointment appointment = new Appointment(this, doctor, date);
        hospital.addAppointment(appointment);
        doctor.addAppointment(appointment);
        System.out.println("Appointment booked with Dr. " + doctor.getName() + " on " + date);
    }

    public void cancelAppointment(Hospital hospital, String appointmentId) 
    {
        Appointment appointment = hospital.findAppointmentById(appointmentId);
        if (appointment != null && appointment.getPatient().equals(this)) 
        {
            hospital.removeAppointment(appointment);
            appointment.getDoctor().removeAppointment(appointment);
            System.out.println("Appointment canceled: " + appointmentId);
        } 
        else 
        {
            System.out.println("Appointment not found or you do not have permission to cancel it.");
        }
    }
}

class Doctor {
    private static int idCounter = 0;
    private String doctorId;
    private String name;
    private String specialization;
    private String phoneNumber;
    private String email;
    private List<Appointment> appointments;

    public Doctor() 
    {
        this.doctorId = "D" + (++idCounter);
        this.appointments = new ArrayList<>();
    }

    // Getters and Setters
    public String getDoctorId() 
    {
        return doctorId;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getSpecialization() 
    {
        return specialization;
    }

    public void setSpecialization(String specialization) 
    {
        this.specialization = specialization;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public List<Appointment> getAppointments() 
    {
        return appointments;
    }

    public void addAppointment(Appointment appointment) 
    {
        this.appointments.add(appointment);
    }

    public void removeAppointment(Appointment appointment) 
    {
        this.appointments.remove(appointment);
    }

    public void registerDoctor() 
    {
        System.out.println("Doctor registered: " + this.name);
    }

    public void updateDoctorDetails() 
    {
        System.out.println("Doctor details updated: " + this.name);
    }

    public void viewDoctorDetails() 
    {
        System.out.println("Doctor ID: " + doctorId);
        System.out.println("Name: " + name);
        System.out.println("Specialization: " + specialization);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);
    }

    public void manageAppointments() 
    {
        System.out.println("Appointments for Dr. " + name);
        for (Appointment appointment : appointments) 
        {
            appointment.viewAppointmentDetails();
        }
    }
}


class Appointment 
{
    private static int idCounter = 0;
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private String appointmentDate;
    private String status;

    public Appointment(Patient patient, Doctor doctor, String appointmentDate) 
    {
        this.appointmentId = "A" + (++idCounter);
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.status = "Scheduled";
    }

    // Getters and Setters
    public String getAppointmentId() 
    {
        return appointmentId;
    }

    public Patient getPatient() 
    {
        return patient;
    }

    public Doctor getDoctor() 
    {
        return doctor;
    }

    public String getAppointmentDate() 
    {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) 
    {
        this.appointmentDate = appointmentDate;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public void scheduleAppointment() 
    {
        System.out.println("Appointment scheduled: " + appointmentId);
    }

    public void cancelAppointment() 
    {
        this.status = "Canceled";
        System.out.println("Appointment canceled: " + appointmentId);
    }

    public void viewAppointmentDetails() 
    {
        System.out.println("Appointment ID: " + appointmentId);
        System.out.println("Patient: " + patient.getName());
        System.out.println("Doctor: " + doctor.getName());
        System.out.println("Date: " + appointmentDate);
        System.out.println("Status: " + status);
    }
}


class Hospital 
{
    private String hospitalName;
    private String hospitalAddress;
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Appointment> appointments;

    public Hospital() 
    {
        this.patients = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    // Getters and Setters
    public String getHospitalName() 
    {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) 
    {
        this.hospitalName = hospitalName;
    }

    public String getHospitalAddress() 
    {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) 
    {
        this.hospitalAddress = hospitalAddress;
    }

    public void addPatient(Patient patient) 
    {
        patients.add(patient);
        patient.registerPatient();
    }

    public void addDoctor(Doctor doctor) 
    {
        doctors.add(doctor);
        doctor.registerDoctor();
    }

    public void addAppointment(Appointment appointment) 
    {
        appointments.add(appointment);
    }

    public void removeAppointment(Appointment appointment) 
    {
        appointments.remove(appointment);
    }

    

    public Patient findPatientById(String patientId) 
    {
        for (Patient patient : patients) 
        {
            if (patient.getPatientId().equals(patientId)) 
            {
                return patient;
            }
        }
        return null;
    }

    public Doctor findDoctorById(String doctorId) 
    {
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorId().equals(doctorId)) 
            {
                return doctor;
            }
        }
        return null;
    }

    

    public Appointment findAppointmentById(String appointmentId) 
    {
        for (Appointment appointment : appointments) 
        {
            if (appointment.getAppointmentId().equals(appointmentId)) 
            {
                return appointment;
            }
        }
        return null;
    }

    public void viewAllPatients() 
    {
        System.out.println("List of Patients:");
        for (Patient patient : patients) 
        {
            patient.viewPatientDetails();
            System.out.println();
        }
    }

    public void viewAllDoctors() 
    {
        System.out.println("List of Doctors:");
        for (Doctor doctor : doctors) 
        {
            doctor.viewDoctorDetails();
            System.out.println();
        }
    }

    public void viewAllAppointments()
    {
        System.out.println("List of Appointments:");
        for (Appointment appointment : appointments) 
        {
            appointment.viewAppointmentDetails();
        }
    }
}

public class Hospital_Management 
{
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) 
    {
        Hospital hospital = new Hospital();
        hospital.setHospitalName("GK HOSPITAL");
        hospital.setHospitalAddress("Sant tukaram nagar pimpri pune - 411018");

        while (true) 
        {
            System.out.println("\nHospital Management System");
            System.out.println("1. Register Patient");
            System.out.println("2. Register Doctor");
            System.out.println("3. Book Appointment");
            System.out.println("4. Cancel Appointment");
            System.out.println("5. View All Patients");
            System.out.println("6. View All Doctors");
            System.out.println("7. View All Appointments");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) 
            {
                case 1:
                    registerPatient(hospital);
                    break;
                case 2:
                    registerDoctor(hospital);
                    break;
                case 3:
                    bookAppointment(hospital);
                    break;
                case 4:
                    cancelAppointment(hospital);
                    break;
                case 5:
                    hospital.viewAllPatients();
                    break;
                case 6:
                    hospital.viewAllDoctors();
                    break;
                case 7:
                    hospital.viewAllAppointments();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerPatient(Hospital hospital) 
    {
        Patient patient = new Patient();
        System.out.print("Enter patient name: ");
        patient.setName(scanner.nextLine());
        System.out.print("Enter patient age: ");
        patient.setAge(scanner.nextInt());
        scanner.nextLine();  // Consume newline
        System.out.print("Enter patient gender: ");
        patient.setGender(scanner.nextLine());
        System.out.print("Enter patient address: ");
        patient.setAddress(scanner.nextLine());
        System.out.print("Enter patient phone number: ");
        patient.setPhoneNumber(scanner.nextLine());
        System.out.print("Enter patient medical history: ");
        patient.setMedicalHistory(scanner.nextLine());

        hospital.addPatient(patient);
    }

    private static void registerDoctor(Hospital hospital) 
    {
        Doctor doctor = new Doctor();
        System.out.print("Enter doctor name: ");
        doctor.setName(scanner.nextLine());
        System.out.print("Enter doctor specialization: ");
        doctor.setSpecialization(scanner.nextLine());
        System.out.print("Enter doctor phone number: ");
        doctor.setPhoneNumber(scanner.nextLine());
        System.out.print("Enter doctor email: ");
        doctor.setEmail(scanner.nextLine());

        hospital.addDoctor(doctor);
    }

    private static void bookAppointment(Hospital hospital) 
    {
        System.out.print("Enter patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter doctor ID: ");
        String doctorId = scanner.nextLine();
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Patient patient = hospital.findPatientById(patientId);
        Doctor doctor = hospital.findDoctorById(doctorId);

        if (patient != null && doctor != null) {
            patient.bookAppointment(hospital, doctor, date);
        } 
        else 
        {
            System.out.println("Invalid patient ID or doctor ID.");
        }
    }

    private static void cancelAppointment(Hospital hospital) 
    {
        System.out.print("Enter appointment ID: ");
        String appointmentId = scanner.nextLine();

        Appointment appointment = hospital.findAppointmentById(appointmentId);
        if (appointment != null) 
        {
            appointment.cancelAppointment();
            hospital.removeAppointment(appointment);
            appointment.getDoctor().removeAppointment(appointment);
        } 
        else 
        {
            System.out.println("Appointment not found.");
        }
    }
}
