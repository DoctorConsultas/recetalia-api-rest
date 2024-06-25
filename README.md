Endpoints and URLs


Get prescriptions by medical provider ID

Description: Retrieves all prescriptions associated with a specific medical provider.

URL: http://localhost:8080/api/prescriptions/by-medical-provider?medicalProviderId=39


Get prescriptions by medic ID and medical provider ID

Description: Retrieves all prescriptions from a specific doctor associated with a specific medical provider.

URL: http://localhost:8080/api/prescriptions/by-medic-and-medical-provider?medicId=88d90c15-223b-4969-8ccc-a53dbffdf0a5&medicalProviderId=39


Get the number of prescriptions by medical provider ID

Description: Retrieves the number of prescriptions issued by doctors associated with a specific medical provider.

URL: http://localhost:8080/api/prescriptions/count-by-medical-provider?medicalProviderId=39


Get active prescriptions by medical provider ID

Description: Retrieves all active prescriptions (status = 'AVAILABLE') from a specific medical provider.

URL: http://localhost:8080/api/prescriptions/active-by-medical-provider?medicalProviderId=39


Get prescriptions by medical provider ID and date range

Description: Retrieves all prescriptions within a specific date range associated with a specific medical provider.

URL: http://localhost:8080/api/prescriptions/by-medical-provider-and-date-range?medicalProviderId=39&startDate=2024-01-01&endDate=2024-02-02


Get prescriptions by medic ID and date range

Description: Retrieves all prescriptions within a specific date range associated with a specific doctor.

URL: http://localhost:8080/api/prescriptions/by-medic-and-date-range?medicId=88d90c15-223b-4969-8ccc-a53dbffdf0a5&startDate=2024-01-01&endDate=2024-02-02


Get all Medics associated with a specific Medical Provider

Description: Retrieves all doctors associated with a specific medical provider.

URL: http://localhost:8080/api/medics/by-medical-provider/39


Get all Patients of a specific Medic associated with a specific Medical Provider

Description: Retrieves all patients of a specific doctor associated with a specific medical provider.

URL: http://localhost:8080/api/patients/by-medic-and-provider?medicId=88d90c15-223b-4969-8ccc-a53dbffdf0a5&medicalProviderId=39

