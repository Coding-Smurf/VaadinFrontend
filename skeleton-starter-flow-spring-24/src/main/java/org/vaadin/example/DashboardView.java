package org.vaadin.example;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterListener;
import com.vaadin.flow.router.Route;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.html.Span;
import jakarta.servlet.http.HttpSession;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.google.gson.Gson;
import com.nimbusds.jose.Header;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.lang.annotation.Native;
import java.net.URI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridLazyDataView;
import com.vaadin.flow.component.grid.dataview.GridListDataView;

import java.util.Arrays;
import java.net.http.HttpClient;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.Grid.Column;
//import Component;
import com.vaadin.flow.component.Component;
//import consumer
import java.util.function.Consumer;
import java.util.stream.Collectors;
//IMport Anchor for download
import com.vaadin.flow.component.html.Anchor;



import com.vaadin.flow.component.html.NativeButton;
//import URL
import java.net.URL;
import java.io.File;
import java.io.FileWriter;











@RolesAllowed("ROLE_USER")
@Route("/dashboard")
public class DashboardView extends VerticalLayout implements BeforeEnterListener {

//! ----------------GLOBAL DECLARATION----------------

    private final SecurityService securityService;

    private int globalId = 0;
    
    private List<TextField> phoneFields = new ArrayList<>();
    private List<TextField> emailFields = new ArrayList<>();
    private List<ComboBox<String>> clientsComboBoxes = new ArrayList<>();
    private List<ComboBox<String>> socialMediaComboBoxes = new ArrayList<>();
    private List<TextField> socialMediaUsernameFields = new ArrayList<>();
    private List<Data> dataList = new ArrayList<>();
    
    private ArrayList<String> items = new ArrayList<>();  
    private ArrayList<String> companies = new ArrayList<>();
    private ArrayList<String> medias = new ArrayList<>();
    private ArrayList<String> sectors = new ArrayList<>();
    private ArrayList<String> positions = new ArrayList<>();
    private ArrayList<String> countries = new ArrayList<>();
    private ArrayList<String> communities = new ArrayList<>();
    private ArrayList<String> regions = new ArrayList<>();
    private ArrayList<String> socialMedia = new ArrayList<>();
    private ArrayList<String> clients = new ArrayList<>();


    private Button getPhoneNumbersButton = new Button("Get Phone Numbers");
    private Button getEmailsButton = new Button("Get Emails");
    private Button getClientsButton = new Button("Get Clients");
    private Button getSocialMediaButton = new Button("Get Social Media");
    private Button getAllButton = new Button("Añadir");
    private Button modifyButton = new Button("Modificar");
    private Button cancelButton = new Button("Cancelar");
    private Button deleteButton = new Button("Eliminar");
    private Button exportButton = new Button("Exportar Todo");
    private Button exporEmailsButton = new Button("Exportar Emails");

    private AppLayout appLayout = new AppLayout();
    private Tab createUsers = new Tab("Campos de Información");
    private Tab modifyUsers = new Tab("Modify Users");
    private Tabs tabs = new Tabs(createUsers, modifyUsers);

    private HorizontalLayout userCreateForm = new HorizontalLayout();
    private VerticalLayout userCreateFormCol1 = new VerticalLayout();
    private VerticalLayout userCreateFormCol2 = new VerticalLayout();
    private VerticalLayout userCreateFormCol3 = new VerticalLayout();
    private VerticalLayout userCreateFormCol4 = new VerticalLayout();
    private VerticalLayout createUsersLayout = new VerticalLayout();

    private VerticalLayout modifyUsersLayout = new VerticalLayout();

    private VerticalLayout nameLayout = new VerticalLayout();
    private VerticalLayout completeNameLayout = new VerticalLayout();
    private VerticalLayout phoneListLayout = new VerticalLayout();
    private VerticalLayout emailListLayout = new VerticalLayout();
    private VerticalLayout companyLayout = new VerticalLayout();
    private VerticalLayout mediaLayout = new VerticalLayout();
    private VerticalLayout sectorLayout = new VerticalLayout();
    private VerticalLayout positionLayout = new VerticalLayout();
    private VerticalLayout personalAddressLayout = new VerticalLayout();
    private VerticalLayout professionalAddressLayout = new VerticalLayout();
    private VerticalLayout countryLayout = new VerticalLayout();
    private VerticalLayout communityLayout = new VerticalLayout();
    private VerticalLayout regionLayout = new VerticalLayout();
    private VerticalLayout ObservationsLayout = new VerticalLayout();
    private VerticalLayout clientsListLayout = new VerticalLayout();
    private VerticalLayout socialMediaListLayout = new VerticalLayout();
    
    private Span nombre = new Span("Nombre");
    private Span nombreCompleto = new Span("Nombre Completo");
    private Span telefono = new Span("Telefono");
    private Span email = new Span("Email");
    private Span empresa = new Span("Empresa");
    private Span medio = new Span("Medio");
    private Span sectorSpan = new Span("Sector");
    private Span cargo = new Span("Cargo");
    private Span direccionPersonal = new Span("Dirección Personal");
    private Span direccionProfesional = new Span("Dirección Profesional");
    private Span pais = new Span("País");
    private Span comunidad = new Span("Comunidad");
    private Span regionSpan = new Span("Región");
    private Span Observaciones = new Span("Observaciones");
    private Span clientes = new Span("Clientes");
    private Span redesSociales = new Span("Redes Sociales");

    private TextField name = new TextField();
    private TextField completeName = new TextField();
    private MultiSelectComboBox<String> company = new MultiSelectComboBox<>();
    private MultiSelectComboBox<String> media = new MultiSelectComboBox<>();
    private MultiSelectComboBox<String> sector = new MultiSelectComboBox<>();
    private MultiSelectComboBox<String> position = new MultiSelectComboBox<>();
    private TextField personalAddress = new TextField();
    private TextField professionalAddress = new TextField();
    private ComboBox<String> country = new ComboBox<>();
    private ComboBox<String> community = new ComboBox<>();
    private ComboBox<String> region = new ComboBox<>();
    private TextArea Observations = new TextArea();



//! ----------------GLOBAL DECLARATION----------------
    
//? --------------------CONSTRUCTOR-------------------

    public DashboardView(SecurityService securityService) {


        this.securityService = securityService;
        createButton();

        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        appLayout.addToDrawer(tabs);

        //call the method that creates the form
        createForm();


        Grid<Data> grid = new Grid<>();
        Grid.Column<Data> idColumn = grid.addColumn(Data::getId);
        Grid.Column<Data> nameColumn = grid.addColumn(Data::getName);
        Grid.Column<Data> fullNameColumn = grid.addColumn(Data::getFullname);
        Grid.Column<Data> phoneColumn = grid.addColumn(Data::getPhone);
        Grid.Column<Data> emailColumn = grid.addColumn(Data::getEmail);
        Grid.Column<Data> companyColumn = grid.addColumn(Data::getCompany);
        Grid.Column<Data> mediaColumn = grid.addColumn(Data::getMedia);
        Grid.Column<Data> sectorColumn = grid.addColumn(Data::getSector);
        Grid.Column<Data> positionColumn = grid.addColumn(Data::getPosition);
        Grid.Column<Data> personalAddressColumn = grid.addColumn(Data::getPersonalAddress);
        Grid.Column<Data> professionalAddressColumn = grid.addColumn(Data::getProfessionalAddress);
        Grid.Column<Data> countryColumn = grid.addColumn(Data::getCountry);
        Grid.Column<Data> communityColumn = grid.addColumn(Data::getCommunity);
        Grid.Column<Data> regionColumn = grid.addColumn(Data::getRegion);
        Grid.Column<Data> observationsColumn = grid.addColumn(Data::getObservations);
        Grid.Column<Data> socialMediaColumn = grid.addColumn(Data::getSocialMedia);
        Grid.Column<Data> clientsColumn = grid.addColumn(Data::getClients);
        idColumn.setVisible(false);
        phoneColumn.setVisible(false);
        personalAddressColumn.setVisible(false);
        professionalAddressColumn.setVisible(false);
        observationsColumn.setVisible(false);

        //show the last introduced data first
        



        loadData();
        grid.setItems(dataList);
        Filter filter = new Filter(grid.setItems(dataList));
        HeaderRow filterRow = grid.appendHeaderRow();

        filterRow.getCell(nameColumn).setComponent(createFilterHeader("Name", filter::setName));
        filterRow.getCell(fullNameColumn).setComponent(createFilterHeader("Full Name", filter::setFullName));
        filterRow.getCell(emailColumn).setComponent(createFilterHeader("Email", filter::setEmail));
        filterRow.getCell(companyColumn).setComponent(createFilterHeader("Company", filter::setCompany));
        filterRow.getCell(mediaColumn).setComponent(createFilterHeader("Media", filter::setMedia));
        filterRow.getCell(sectorColumn).setComponent(createFilterHeader("Sector", filter::setSector));
        filterRow.getCell(positionColumn).setComponent(createFilterHeader("Position", filter::setPosition));
        filterRow.getCell(countryColumn).setComponent(createFilterHeader("Country", filter::setCountry));
        filterRow.getCell(communityColumn).setComponent(createFilterHeader("Community", filter::setCommunity));
        filterRow.getCell(regionColumn).setComponent(createFilterHeader("Region", filter::setRegion));
        filterRow.getCell(socialMediaColumn).setComponent(createFilterHeader("Social Media", filter::setSocialMedia));
        filterRow.getCell(clientsColumn).setComponent(createFilterHeader("Clients", filter::setClients));

        //when clickin a row, fill the form with the data of the selected row
        grid.addItemClickListener(event -> {
            try{
            
            //get the data which has the same id as the selected row
            Data selectedData = dataList.stream().filter(data -> data.getId() == event.getItem().getId()).findFirst().get();
            
            //set the global id to the id of the selected data
            globalId = selectedData.getId();
            String name = selectedData.getName();
            String fullName = selectedData.getFullname();
            this.name.setValue(name);
            this.completeName.setValue(fullName);
            String phone = selectedData.getPhone();
            //separate the phone numbers
            String[] phoneNumbers = phone.split(", ");
                //check if the phone fields are enough to fill with the phone numbers
                if (phoneFields.size() < phoneNumbers.length) {
                    //add the necessary phone fields
                    for (int i = phoneFields.size(); i < phoneNumbers.length; i++) {
                        addPhoneField(phoneListLayout);
                    }
                }
                //fill the phone fields with the phone numbers
                for (int i = 0; i < phoneNumbers.length; i++) {
                    phoneFields.get(i).setValue(phoneNumbers[i]);
                }

            String email = selectedData.getEmail();
            //separate the emails
            String[] emails = email.split(", ");
                //check if the email fields are enough to fill with the emails
                if (emailFields.size() < emails.length) {
                    //add the necessary email fields
                    for (int i = emailFields.size(); i < emails.length; i++) {
                        addEmailField(emailListLayout);
                    }
                }
                //fill the email fields with the emails
                for (int i = 0; i < emails.length; i++) {
                    emailFields.get(i).setValue(emails[i]);
                }

            String company = selectedData.getCompany();
            //fill the company field
            this.company.setValue(company.split(", "));
            String media = selectedData.getMedia();
            //fill the media field
            this.media.setValue(media.split(", "));
            String sector = selectedData.getSector();
            //fill the sector field
            this.sector.setValue(sector.split(", "));
            String position = selectedData.getPosition();
            //fill the position field
            this.position.setValue(position.split(", "));
            String personalAddress = selectedData.getPersonalAddress();
            //fill the personal address field
            this.personalAddress.setValue(personalAddress);
            String professionalAddress = selectedData.getProfessionalAddress();
            //fill the professional address field
            this.professionalAddress.setValue(professionalAddress);
            String country = selectedData.getCountry();
            //fill the country field
            this.country.setValue(country);
            String community = selectedData.getCommunity();
            //fill the community field
            this.community.setValue(community);
            String region = selectedData.getRegion();
            //fill the region field
            this.region.setValue(region);
            String observations = selectedData.getObservations();
            //fill the observations field
            this.Observations.setValue(observations);

            String socialMedia = selectedData.getSocialMedia();
            //separate the social media
            String[] socialMedias = socialMedia.split(", ");
                //check if the social media fields are enough to fill with the social media
                if (socialMediaComboBoxes.size() < socialMedias.length) {
                    //add the necessary social media fields
                    for (int i = socialMediaComboBoxes.size(); i < socialMedias.length; i++) {
                        addSocialMediaField(socialMediaListLayout);
                    }
                }
                //fill the social media fields with the social media
                for (int i = 0; i < socialMedias.length; i++) {
                    //separate socialmedias[i] by the character | in 2 parts
                    String[] socialMediaParts = socialMedias[i].split("\\|");
                    //fill the social media combobox with the first part
                    socialMediaComboBoxes.get(i).setValue(socialMediaParts[0]);
                    //fill the social media username field with the second part
                    socialMediaUsernameFields.get(i).setValue(socialMediaParts[1]);
                }

            String clients = selectedData.getClients();
            //separate the clients
            String[] clientsArray = clients.split(", ");
                //check if the clients fields are enough to fill with the clients
                if (clientsComboBoxes.size() < clientsArray.length) {
                    //add the necessary clients fields
                    for (int i = clientsComboBoxes.size(); i < clientsArray.length; i++) {
                        addClientsComboBox(clientsListLayout);
                    }
                }
                //fill the clients fields with the clients
                for (int i = 0; i < clientsArray.length; i++) {
                    clientsComboBoxes.get(i).setValue(clientsArray[i]);
                }
            } catch (Exception e) {
                System.out.println(e);
            }

            //hide the accept button and show the modify button
            getAllButton.setVisible(false);
            modifyButton.setVisible(true);
            cancelButton.setVisible(true);
            deleteButton.setVisible(true);


            showLayout(createUsersLayout);
            //change the tab to create users
            tabs.setSelectedTab(createUsers);
        });

        //when clicking the modify button, modify the data of the selected row
        modifyButton.addClickListener(event -> {
            modifyData();
        });
        
        deleteButton.addClickListener(event -> {
            deleteData();
        });

        exportButton.addClickListener(event -> {
            //get the list of filtered data from the filtered rows
            //dont use query
            List<Data> filteredData = dataList.stream().filter(data -> filter.filter(data)).collect(Collectors.toList());
            //export the filtered data to a csv file
            exportData(filteredData);

            

        });

        exporEmailsButton.addClickListener(event -> {
            //get the list of filtered data from the filtered rows
            //dont use query
            List<Data> filteredData = dataList.stream().filter(data -> filter.filter(data)).collect(Collectors.toList());
            //export the filtered data to a csv file
            exportLineOfEmailsandNames(filteredData);
        });


         

        
        
        //+ ----------------PROPER LAYOUT----------------
        userCreateFormCol1.add(nameLayout, completeNameLayout);
        addPhoneField(phoneListLayout);
        getPhoneNumbersButton.addClickListener(event -> {
            String phoneNumbers = getPhoneNumbers();
            Notification.show(phoneNumbers);});
        addEmailField(emailListLayout);
        getEmailsButton.addClickListener(event -> {
            String emails = getEmails();
            Notification.show(emails);});
        userCreateFormCol1.add(phoneListLayout);
        userCreateFormCol1.add(emailListLayout);

        userCreateFormCol2.add(companyLayout, mediaLayout, sectorLayout, positionLayout);
        userCreateFormCol3.add(personalAddressLayout, professionalAddressLayout, countryLayout, communityLayout, regionLayout);
        userCreateFormCol4.add(ObservationsLayout);

        getSocialMediaButton.addClickListener(event -> {
            String socialMedia = getSocialMedia();
            Notification.show(socialMedia);});
        addSocialMediaField(socialMediaListLayout);
        userCreateFormCol4.add(socialMediaListLayout);

        getClientsButton.addClickListener(event -> {
            String clients = getClients();
            Notification.show(clients);});
        addClientsComboBox(clientsListLayout);
        userCreateFormCol4.add(clientsListLayout);




        userCreateForm.add(userCreateFormCol1, userCreateFormCol2, userCreateFormCol3, userCreateFormCol4);
        
        HorizontalLayout leftButtonsLayout = new HorizontalLayout(getAllButton, modifyButton, cancelButton);
        HorizontalLayout rightButtonsLayout = new HorizontalLayout(deleteButton);

        HorizontalLayout buttonsLayout = new HorizontalLayout( leftButtonsLayout, rightButtonsLayout);


        leftButtonsLayout.getStyle().set("margin-left", "0");
        rightButtonsLayout.getStyle().set("margin-left", "auto");
        
        

        buttonsLayout.setWidth("100%");
        createUsersLayout.add(userCreateForm, buttonsLayout);

        HorizontalLayout exporButtonsLayout = new HorizontalLayout(exporEmailsButton, exportButton);
        modifyUsersLayout.add(grid, exporButtonsLayout);

        
        //+ ----------------PROPER LAYOUT----------------

        showLayout(createUsersLayout);
         tabs.addSelectedChangeListener(event -> {

            //if the changed toab is not to create users, then reset the form
            if (!event.getSelectedTab().getLabel().equals("Campos de Información")) {
                resetForm();
            }

            if (event.getSelectedTab().getLabel().equals("Campos de Información")) {
                // Show the createUsersLayout when the "Create Users" tab is selected
                showLayout(createUsersLayout);
            }
            else if (event.getSelectedTab().getLabel().equals("Modify Users")) {
                // Show the modifyUsersLayout when the "Modify Users" tab is selected
                loadData();
                showLayout(modifyUsersLayout);
            }
        });
        add(appLayout);
    }

 

    public void loadData(){
        //GET request to the server
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://springbackend-production.up.railway.app/get"))
            .header("Content-Type", "application/json")
            .GET()
            .build();
        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            

            Gson gson = new Gson();
            Data[] data = gson.fromJson(responseBody, Data[].class);
            dataList.clear();
            dataList.addAll(Arrays.asList(data));
            //flip the list to show the last introduced data first
            dataList.sort((a, b) -> b.getId() - a.getId());


        } catch (Exception e) {
            System.out.println(e);
        }
 
    }

    private static class Filter{

        private final GridListDataView<Data> dataView;

        private String name;
        private String fullName;
        private String phone;
        private String email;
        private String company;
        private String media;
        private String sector;
        private String position;
        private String personalAddress;
        private String professionalAddress;
        private String country;
        private String community;
        private String region;
        private String observations;
        private String socialMedia;
        private String clients;

        public Filter(GridListDataView<Data> dataView) {
            this.dataView = dataView;
            this.dataView.addFilter(this::filter);
        }

        public void setName(String name) {
            this.name = name;
            dataView.refreshAll();
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
            dataView.refreshAll();
        }

        public void setPhone(String phone) {
            this.phone = phone;
            dataView.refreshAll();
        }

        public void setEmail(String email) {
            this.email = email;
            dataView.refreshAll();
        }

        public void setCompany(String company) {
            this.company = company;
            dataView.refreshAll();
        }

        public void setMedia(String media) {
            this.media = media;
            dataView.refreshAll();
        }

        public void setSector(String sector) {
            this.sector = sector;
            dataView.refreshAll();
        }

        public void setPosition(String position) {
            this.position = position;
            dataView.refreshAll();
        }

        public void setPersonalAddress(String personalAddress) {
            this.personalAddress = personalAddress;
            dataView.refreshAll();
        }

        public void setProfessionalAddress(String professionalAddress) {
            this.professionalAddress = professionalAddress;
            dataView.refreshAll();
        }

        public void setCountry(String country) {
            this.country = country;
            dataView.refreshAll();
        }

        public void setCommunity(String community) {
            this.community = community;
            dataView.refreshAll();
        }

        public void setRegion(String region) {
            this.region = region;
            dataView.refreshAll();
        }

        public void setObservations(String observations) {
            this.observations = observations;
            dataView.refreshAll();
        }

        public void setSocialMedia(String socialMedia) {
            this.socialMedia = socialMedia;
            dataView.refreshAll();
        }

        public void setClients(String clients) {
            this.clients = clients;
            dataView.refreshAll();
        }

        public boolean filter (Data data){
            boolean nameMatch = matches(data.getName(), name);
            boolean fullNameMatch = matches(data.getFullname(), fullName);
            boolean phoneMatch = matches(data.getPhone(), phone);
            boolean emailMatch = matches(data.getEmail(), email);
            boolean companyMatch = matches(data.getCompany(), company);
            boolean mediaMatch = matches(data.getMedia(), media);
            boolean sectorMatch = matches(data.getSector(), sector);
            boolean positionMatch = matches(data.getPosition(), position);
            boolean personalAddressMatch = matches(data.getPersonalAddress(), personalAddress);
            boolean professionalAddressMatch = matches(data.getProfessionalAddress(), professionalAddress);
            boolean countryMatch = matches(data.getCountry(), country);
            boolean communityMatch = matches(data.getCommunity(), community);
            boolean regionMatch = matches(data.getRegion(), region);
            boolean observationsMatch = matches(data.getObservations(), observations);
            boolean socialMediaMatch = matches(data.getSocialMedia(), socialMedia);
            boolean clientsMatch = matches(data.getClients(), clients);

            return nameMatch && fullNameMatch && phoneMatch && emailMatch && companyMatch && mediaMatch && sectorMatch && positionMatch && personalAddressMatch && professionalAddressMatch && countryMatch && communityMatch && regionMatch && observationsMatch && socialMediaMatch && clientsMatch;
        }

        private boolean matches(String value, String filter){
            if (filter == null || filter.isEmpty()){
                return true;
            }
            return value.toLowerCase().contains(filter.toLowerCase());
        }

    }

    private static Component createFilterHeader(String labelText, Consumer<String> setter) {
        
        NativeLabel label = new NativeLabel(labelText);
        label.getStyle().set("padding-top", "var(--lumo-space-m)")
            .set("font-size", "var(--lumo-font-size-xs)");
        TextField textField = new TextField();
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.setClearButtonVisible(true);
        textField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        textField.setWidthFull();
        textField.getStyle().set("max-width", "100%");
        textField.addValueChangeListener(event -> setter.accept(event.getValue()));
        VerticalLayout layout = new VerticalLayout(label, textField);
        layout.getThemeList().clear();
        layout.getThemeList().add("spacing-xs");
        return layout;
    }   

    private void exportData(List<Data> dataList){
        //create a string with the headers of the csv file
        String csv = "Name;Full Name;Phone;Email;Company;Media;Sector;Position;Personal Address;Professional Address;Country;Community;Region;Observations;Social Media;Clients\n";
        //add the data of each data object to the csv string
        for (Data data : dataList) {
            csv += data.getName() + ";" + data.getFullname() + ";" + data.getPhone() + ";" + data.getEmail() + ";" + data.getCompany() + ";" + data.getMedia() + ";" + data.getSector() + ";" + data.getPosition() + ";" + data.getPersonalAddress() + ";" + data.getProfessionalAddress() + ";" + data.getCountry() + ";" + data.getCommunity() + ";" + data.getRegion() + ";" + data.getObservations() + ";" + data.getSocialMedia() + ";" + data.getClients() + "\n";
        }
        //Create a new file with the csv string
        try {
            //avoid access denied error
            String projectRoot = System.getProperty("user.dir");
            File file = new File(projectRoot + "/data.csv");
            FileWriter writer = new FileWriter(file);
            writer.write(csv);
            writer.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void exportLineOfEmailsandNames(List<Data> dataList){
        //create a string with the headers of the csv file
        String csv = "Name;Email\n";
        //add the data of each data object to the csv string
        for (Data data : dataList) {
            //if the email field is empty, then skip the data object
            if (data.getEmail().isEmpty()) {
                continue;
            }

            //separate the emails
            String[] emails = data.getEmail().split(", ");
            //fill the csv string with the name and email of each data object
            for (String email : emails) {
                csv += data.getName() + ";" + email + "\n";
            }
        }
        //Create a new file with the csv string
        try {
            //avoid access denied error
            String projectRoot = System.getProperty("user.dir");
            File file = new File(projectRoot + "/emails.csv");
            FileWriter writer = new FileWriter(file);
            writer.write(csv);
            writer.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
















    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (!isLoggedIn()) {
            event.rerouteTo("login");
        }
    }
    private boolean isLoggedIn() { 
        HttpSession session = (HttpSession) UI.getCurrent().getSession().getSession();
        return session.getAttribute("user") != null;
    }

    private void createButton() {
        H1 logo = new H1("DmDima Panel");
        logo.addClassNames("text-l", "m-m");

        Button logout = new Button("Log out", e -> securityService.logout());
        logout.getStyle().set("color", "red");
        

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout); 

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo); 
        header.setWidth("100%");
        header.addClassNames("py-0", "px-m");

        appLayout.addToNavbar(header);
    }
    private void showLayout(VerticalLayout layoutToShow) {
        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.removeAll();
        mainLayout.add(layoutToShow);
        appLayout.setContent(mainLayout);
    }

    private void createForm() {
        userCreateFormCol1.getStyle().set("padding", "0");
        userCreateFormCol2.getStyle().set("padding", "0");
        userCreateFormCol3.getStyle().set("padding", "0");
        userCreateFormCol4.getStyle().set("padding", "0");

        appLayout.getStyle().set("width", "100%");
        userCreateForm.getStyle().set("width", "100%");
        userCreateFormCol4.getStyle().set("width", "100%");
        ObservationsLayout.getStyle().set("width", "100%");
        Observations.setWidth("100%");
        Observations.setMaxHeight("172px");
        
        
        nameLayout.setPadding(false);
        name.setHelperText("Ej: Jack");

        name.getStyle().set("padding-top", "0");
        nombre.getStyle().set("padding-bottom", "0").set("padding-top", "0").set("margin-top", "0").set("margin-bottom", "0");
        nameLayout.add(nombre,name);

        completeNameLayout.setPadding(false);
        completeName.setHelperText("Ej: Jack Sparrow");
        completeName.getStyle().set("padding-top", "0");
        nombreCompleto.getStyle().set("padding-bottom", "0").set("padding-top", "0").set("margin-top", "0px").set("margin-bottom", "0");
        completeNameLayout.add(nombreCompleto, completeName);

        //once the name field is filled, write its value in the complete name field
        name.addValueChangeListener(event -> {
            //if the complete name field is empty, then write the name field value in it
            if (completeName.isEmpty()) {
                completeName.setValue(name.getValue());
            }
        });

        //once the complete name field is filled, separate the first name and last name and write the first name in the name field
        completeName.addValueChangeListener(event -> {
            //if the name field is empty, then write the first name in it
            if (name.isEmpty()) {
                String[] names = completeName.getValue().split(" ");
                name.setValue(names[0]);
            }
        });

        telefono.getStyle().set("padding-bottom", "0").set("padding-top", "0").set("margin-top", "0px").set("margin-bottom", "0");
        phoneListLayout.add(telefono);

        email.getStyle().set("padding-bottom", "0").set("padding-top", "0").set("margin-top", "0px").set("margin-bottom", "0");
        emailListLayout.add(email);

        clientes.getStyle().set("padding-bottom", "0").set("padding-top", "0").set("margin-top", "0px").set("margin-bottom", "0");
        clientsListLayout.add(clientes);

        redesSociales.getStyle().set("padding-bottom", "0").set("padding-top", "0").set("margin-top", "0px").set("margin-bottom", "0");
        socialMediaListLayout.add(redesSociales);
        
        companyLayout.setPadding(false);
        company.setHelperText("Ej: Perla Negra S.A.");
        company.getStyle().set("padding-top", "0");
        empresa.getStyle().set("padding-bottom", "0").set("padding-top", "0").set("margin-top", "0px").set("margin-bottom", "0");
        companyLayout.add(empresa, company);

        
        
        
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");
        company.setItems(items);
        company.setAllowCustomValue(true);
        company.setClearButtonVisible(true);
        company.addCustomValueSetListener(event -> {
            Set<String> selectedItems = company.getValue();
            String customValue = event.getDetail();
            customValue = customValue.replaceAll("[,;]", "");
            ArrayList<String> newItems = new ArrayList<>(selectedItems);
            newItems.add(customValue);
            company.setValue(newItems);
        });

        mediaLayout.setPadding(false);
        media.setHelperText("Ej: Cine");
        media.getStyle().set("padding-top", "0");
        medio.getStyle().set("padding-bottom", "0").set("padding-top", "0").set("margin-top", "0px").set("margin-bottom", "0");
        mediaLayout.add(medio, media);

        media.setItems(items);
        media.setAllowCustomValue(true);
        media.setClearButtonVisible(true);
        media.addCustomValueSetListener(event -> {
            Set<String> selectedItems = media.getValue();
            String customValue = event.getDetail();
            customValue = customValue.replaceAll("[,;]", "");
            ArrayList<String> newItems = new ArrayList<>(selectedItems);
            newItems.add(customValue);
            media.setValue(newItems);
        });

        sectorLayout.setPadding(false);
        sector.setHelperText("Ej: Piratería");
        sector.getStyle().set("padding-top", "0");
        sectorSpan.getStyle().set("padding-bottom", "0").set("padding-top", "0").set("margin-top", "0px").set("margin-bottom", "0");
        sectorLayout.add(sectorSpan, sector);

        sector.setItems(items);
        sector.setAllowCustomValue(true);
        sector.setClearButtonVisible(true);
        sector.addCustomValueSetListener(event -> {
            Set<String> selectedItems = sector.getValue();
            String customValue = event.getDetail();
            customValue = customValue.replaceAll("[,;]", "");
            ArrayList<String> newItems = new ArrayList<>(selectedItems);
            newItems.add(customValue);
            sector.setValue(newItems);
        });


        positionLayout.setPadding(false);
        position.setHelperText("Ej: Capitán");
        position.getStyle().set("padding-top", "0");
        cargo.getStyle().set("padding-bottom", "0").set("padding-top", "0").set("margin-top", "0px").set("margin-bottom", "0");
        positionLayout.add(cargo, position);

        position.setItems(items);
        position.setAllowCustomValue(true);
        position.setClearButtonVisible(true);
        position.addCustomValueSetListener(event -> {
            Set<String> selectedItems = position.getValue();
            String customValue = event.getDetail();
            customValue = customValue.replaceAll("[,;]", "");
            ArrayList<String> newItems = new ArrayList<>(selectedItems);
            newItems.add(customValue);
            position.setValue(newItems);
        });

        personalAddressLayout.setPadding(false);
        personalAddress.setHelperText("Ej: C/Muelle 13, Tortuga");
        personalAddress.getStyle().set("padding-top", "0");
        direccionPersonal.getStyle().set("padding-bottom", "0").set("padding-top", "0").set("margin-top", "0").set("margin-bottom", "0");
        personalAddressLayout.add(direccionPersonal, personalAddress);

        professionalAddressLayout.setPadding(false);
        professionalAddress.setHelperText("Ej: C/Navío 2, Tortuga");
        professionalAddress.getStyle().set("padding-top", "0");
        direccionProfesional.getStyle().set("padding-bottom", "0").set("padding-top", "0").set("margin-top", "0px").set("margin-bottom", "0");
        professionalAddressLayout.add(direccionProfesional, professionalAddress);

        
        countryLayout.setPadding(false);
        country.setHelperText("Ej: Costa Rica");
        country.getStyle().set("padding-top", "0");
        pais.getStyle().set("padding-bottom", "0").set("padding-top", "0").set("margin-top", "0px").set("margin-bottom", "0");
        countryLayout.add(pais, country);
        country.setItems(items);

        country.addCustomValueSetListener(event -> {
            String customValue = event.getDetail();
            //remove , and ; characters from the custom value
            customValue = customValue.replaceAll("[,;]", "");
            if (items.contains(customValue)) {
                country.setValue(customValue);
                return;
            }
            items.add(customValue);
            country.setItems(items);
            country.setValue(customValue);
        });

        communityLayout.setPadding(false);
        community.setHelperText("Ej: Caribe Occidental");
        community.getStyle().set("padding-top", "0");
        comunidad.getStyle().set("padding-bottom", "0").set("padding-top", "0").set("margin-top", "0px").set("margin-bottom", "0");
        communityLayout.add(comunidad, community);
        community.setItems(items);

        community.addCustomValueSetListener(event -> {
            String customValue = event.getDetail();
            //remove , and ; characters from the custom value
            customValue = customValue.replaceAll("[,;]", "");
            if (items.contains(customValue)) {
                community.setValue(customValue);
                return;
            }
            items.add(customValue);
            community.setItems(items);
            community.setValue(customValue);
        });

        regionLayout.setPadding(false);
        region.setHelperText("Ej: Isla Tortuga");
        region.getStyle().set("padding-top", "0");
        regionSpan.getStyle().set("padding-bottom", "0").set("padding-top", "0").set("margin-top", "0px").set("margin-bottom", "0");
        regionLayout.add(regionSpan, region);
        region.setItems(items);

        region.addCustomValueSetListener(event -> {
            String customValue = event.getDetail();
            //remove , and ; characters from the custom value
            customValue = customValue.replaceAll("[,;]", "");
            if (items.contains(customValue)) {
                region.setValue(customValue);
                return;
            }
            items.add(customValue);
            region.setItems(items);
            region.setValue(customValue);
        });

        
        ObservationsLayout.setPadding(false);
        Observations.setHelperText("Ej: Jamás enseñarle dónde guardamos el ron");
        Observations.getStyle().set("padding-top", "0");
        Observaciones.getStyle().set("padding-bottom", "0").set("padding-top", "0").set("margin-top", "0").set("margin-bottom", "0");
        ObservationsLayout.add(Observaciones, Observations);
        //make the observations field as wide as possible
        
        //me the field tall so that it can be seen without scrolling using --lumo-text-field-size: var(--lumo-size-s); 
        Observations.getStyle().set("height", "calc(var(--lumo-text-field-size) * 5)");
        

        //make the getallbutton bigger and more visible
        getAllButton.getStyle().set("background-color", "blue").set("color", "white").set("font-size", "20px");
        getAllButton.addClickListener(event -> {
            saveData();
        });

        modifyButton.getStyle().set("background-color", "blue").set("color", "white").set("font-size", "20px");

        //hide the modify button and the cancel button
        modifyButton.setVisible(false);

        cancelButton.getStyle().set("background-color", "white").set("color", "red").set("font-size", "20px");
        cancelButton.getStyle().set("border", "1px solid red");
        cancelButton.addClickListener(event -> {
            resetForm();
        });

        //hide
        cancelButton.setVisible(false);


        deleteButton.getStyle().set("background-color", "red").set("color", "white").set("font-size", "20px");
        
        //float the button to the right
        deleteButton.getStyle().set("float", "right");

        //hide
        deleteButton.setVisible(false);

        


    }
    private void addPhoneField( VerticalLayout phoneListLayout) {
        HorizontalLayout phoneFieldLayout = new HorizontalLayout();
        phoneFieldLayout.setPadding(false);
        phoneListLayout.setPadding(false);
        TextField phoneField = createPhoneField();
        Button actionButton = new Button("+");
        actionButton.getStyle().set("margin-top", "3px");
        
        actionButton.addClickListener(event -> {
            if (actionButton.getText().equals("+")) {
                addPhoneField(phoneListLayout);
                actionButton.setText("-");
                actionButton.getStyle().set("color", "red");
            } else {
                removePhoneField(phoneFieldLayout);
            }
        });

        actionButton.setEnabled(false);

        //type listener
        phoneField.addValueChangeListener(event -> {

            //if the button says delete, then enable it and change its color to red
            if (actionButton.getText().equals("-")) {
                actionButton.setEnabled(true);
                return;
            }
            if (phoneField.isInvalid() || phoneField.getValue().isEmpty() || phoneField.getValue().isBlank()) {
                actionButton.setEnabled(false);
            } else {
                actionButton.setEnabled(true);
            }
        });


        phoneFields.add(phoneField);
        phoneFieldLayout.add(phoneField, actionButton);
        phoneListLayout.add(phoneFieldLayout);
        
    }
    private void addEmailField(VerticalLayout emailListLayout) {
        HorizontalLayout emailFieldLayout = new HorizontalLayout();
        emailFieldLayout.setPadding(false);
        emailListLayout.setPadding(false);
        TextField emailField = createEmailField();
        Button actionButton = new Button("+");
        actionButton.getStyle().set("margin-top", "3px");
        actionButton.addClickListener(event -> {
            if (actionButton.getText().equals("+")) {
                addEmailField(emailListLayout);
                actionButton.setText("-");
                actionButton.getStyle().set("color", "red");
            } else {
                removeEmailField(emailFieldLayout);
            }
        });

        actionButton.setEnabled(false);

        //type listener
        emailField.addValueChangeListener(event -> {


            //if the button says delete, then enable it and change its color to red
            if (actionButton.getText().equals("-")) {
                actionButton.setEnabled(true);
                return;
            }
            if (emailField.isInvalid() || emailField.getValue().isEmpty() || emailField.getValue().isBlank()) {
                actionButton.setEnabled(false);
            } else {
                actionButton.setEnabled(true);
            }
        });


        emailFields.add(emailField);
        emailFieldLayout.add(emailField, actionButton);
        emailListLayout.add(emailFieldLayout);
    }
    private void addClientsComboBox(VerticalLayout clientsListLayout) {
        HorizontalLayout clientsComboBoxLayout = new HorizontalLayout();
        clientsComboBoxLayout.setPadding(false);
        clientsListLayout.setPadding(false);
        ComboBox<String> clientsComboBox = createClientsComboBox();
        Button actionButton = new Button("+");
        actionButton.getStyle().set("margin-top", "3px");
        actionButton.addClickListener(event -> {
            if (actionButton.getText().equals("+")) {
                addClientsComboBox(clientsListLayout);
                actionButton.setText("-");
                actionButton.getStyle().set("color", "red");
            } else {
                removeClientsComboBox(clientsComboBoxLayout);
            }
        });

        actionButton.setEnabled(false);

        //type listener when a custom value is entered add it to the items list
        clientsComboBox.addCustomValueSetListener(event -> {
            String customValue = event.getDetail();
            //unallow , and ; characters and remove them from the custom value
            customValue = customValue.replaceAll("[,;]", "");
            if (items.contains(customValue)) {
                clientsComboBox.setValue(customValue);
                return;
            }
            items.add(customValue);
            clientsComboBox.setItems(items);
            clientsComboBox.setValue(customValue);
        });

        //type listener
        clientsComboBox.addValueChangeListener(event -> {
            try
            {
                //if the button says delete, then enable it and change its color to red
                if (actionButton.getText().equals("-")) {
                    actionButton.setEnabled(true);
                    return;
                }
                if (clientsComboBox.isInvalid() || clientsComboBox.getValue().isEmpty() || clientsComboBox.getValue().isBlank()) {
                    actionButton.setEnabled(false);
                } else {
                    actionButton.setEnabled(true);
                }
            }
            catch (Exception e)
            {
                clientsComboBox.addCustomValueSetListener(ev -> {
                    String customValue = ev.getDetail();
                    //remove , and ; characters from the custom value
                    customValue = customValue.replaceAll("[,;]", "");
                    //if the custom value is already in the items list, then, dont add it
                    if (items.contains(customValue)) {
                        clientsComboBox.setValue(customValue);
                        return;
                    }
                    items.add(customValue);
                    clientsComboBox.setItems(items);
                    clientsComboBox.setValue(customValue);
                });
            }
            
        });


        

        clientsComboBoxes.add(clientsComboBox);
        clientsComboBoxLayout.add(clientsComboBox, actionButton);
        clientsListLayout.add(clientsComboBoxLayout);
    }
    private void addSocialMediaField(VerticalLayout socialMediaListLayout) {
        HorizontalLayout socialMediaFieldLayout = new HorizontalLayout();
        socialMediaFieldLayout.setPadding(false);
        socialMediaListLayout.setPadding(false);
        ComboBox<String> socialMediaComboBox = createSocialMediaComboBox();
        TextField socialMediaUsernameField = createSocialMediaUsernameField();
        Button actionButton = new Button("+");
        actionButton.getStyle().set("margin-top", "3px");
        actionButton.addClickListener(event -> {
            if (actionButton.getText().equals("+")) {
                addSocialMediaField(socialMediaListLayout);
                actionButton.setText("-");
                actionButton.getStyle().set("color", "red");
            } else {
                removeSocialMediaField(socialMediaFieldLayout);
            }
        });

        actionButton.setEnabled(false);

        socialMediaComboBox.addCustomValueSetListener(event -> {
            String customValue = event.getDetail();
            //remove , and ; characters from the custom value
            customValue = customValue.replaceAll("[,;|]", "");
            if (items.contains(customValue)) {
                socialMediaComboBox.setValue(customValue);
                return;
            }
            items.add(customValue);
            socialMediaComboBox.setItems(items);
            socialMediaComboBox.setValue(customValue);
        });

        socialMediaComboBox.addValueChangeListener(event->{
            try
            {
                //if the button says delete, then enable it and change its color to red
                if (actionButton.getText().equals("-")) {
                    return;
                }
                if (socialMediaComboBox.isInvalid() || socialMediaComboBox.getValue().isEmpty() || socialMediaComboBox.getValue().isBlank()) {
                    return;
                }
            }
            catch (Exception e)
            {
                socialMediaComboBox.addCustomValueSetListener(ev -> {
                    String customValue = ev.getDetail();
                    //remove , and ; characters from the custom value
                    customValue = customValue.replaceAll("[,;|]", "");
                    if (items.contains(customValue)) {
                        socialMediaComboBox.setValue(customValue);
                        return;
                    }
                    items.add(customValue);
                    socialMediaComboBox.setItems(items);
                    socialMediaComboBox.setValue(customValue);
                });
            }
        });

        //type listener
        socialMediaUsernameField.addValueChangeListener(event -> {

            //remove , and ; characters from the custom value
            String value = socialMediaUsernameField.getValue().replaceAll("[,;]", "");
            socialMediaUsernameField.setValue(value);

            //if the button says delete, then enable it and change its color to red
            if (actionButton.getText().equals("-")) {
                actionButton.setEnabled(true);
                return;
            }
            if (socialMediaUsernameField.isInvalid() || socialMediaUsernameField.getValue().isEmpty() || socialMediaUsernameField.getValue().isBlank()) {
                actionButton.setEnabled(false);
            } else {
                actionButton.setEnabled(true);
            }
        });

        socialMediaComboBoxes.add(socialMediaComboBox);
        socialMediaUsernameFields.add(socialMediaUsernameField);
        socialMediaFieldLayout.add(socialMediaComboBox, socialMediaUsernameField, actionButton);
        socialMediaListLayout.add(socialMediaFieldLayout);
    }
    private TextField createPhoneField() {
        TextField phoneField = new TextField();
        phoneField.setHelperText("Ej: +(34) 123 456 789");
        phoneField.getStyle().set("margin-top", "0");
        phoneField.getStyle().set("margin-bottom", "0");
        phoneField.getStyle().set("padding-bottom", "0");
        phoneField.setPattern("^\\+?(\\d{1,3})?\\s*\\(?(\\d{1,4})\\)?[\\s.-]*\\d{1,4}[\\s.-]*\\d{1,4}[\\s.-]*\\d{1,9}$");
        phoneField.setAllowedCharPattern("[0-9()+\\- ]");
        phoneField.setMinLength(5);
        phoneField.setMaxLength(18);
        return phoneField;
    }
    private TextField createEmailField() {
        TextField emailField = new TextField();
        emailField.setHelperText("Ej: jack@piratesmail.com");
        emailField.getStyle().set("margin-top", "0");
        emailField.getStyle().set("margin-bottom", "0");
        emailField.getStyle().set("padding-bottom", "0");
        emailField.setPattern("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        emailField.setMinLength(5);
        emailField.setMaxLength(50);
        emailField.setValueChangeMode(ValueChangeMode.EAGER);
        emailField.addValueChangeListener(event -> {
            String value = event.getValue();
            String filteredValue = value.replaceAll("[,;]", "");
            if (!value.equals(filteredValue)) {
                emailField.setValue(filteredValue);
            }
        });
        
        return emailField;
    }
    private ComboBox<String> createClientsComboBox() {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setHelperText("Ej: Davy Jones S.L.");
        comboBox.getStyle().set("margin-top", "0");
        comboBox.getStyle().set("margin-bottom", "0");
        comboBox.getStyle().set("padding-bottom", "0");
        comboBox.setItems("Davy Jones S.L.", "Barbanegra S.A.", "Perla Negra S.A.");
        comboBox.setAllowCustomValue(true);
        return comboBox;
    }
    private ComboBox<String> createSocialMediaComboBox() {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setHelperText("Ej: Facebook");
        comboBox.getStyle().set("margin-top", "0");
        comboBox.getStyle().set("margin-bottom", "0");
        comboBox.getStyle().set("padding-bottom", "0");
        comboBox.setItems("Facebook", "Twitter", "Instagram");
        comboBox.setAllowCustomValue(true);
        return comboBox;
    }
    private TextField createSocialMediaUsernameField() {
        TextField usernameField = new TextField();
        usernameField.setHelperText("Ej: @JackSparrow");
        usernameField.getStyle().set("margin-top", "0");
        usernameField.getStyle().set("margin-bottom", "0");
        usernameField.getStyle().set("padding-bottom", "0");
        usernameField.setMinLength(3);
        usernameField.setMaxLength(50);
        return usernameField;
    }
    private void removePhoneField(HorizontalLayout phoneFieldLayout) {
        TextField phoneField = (TextField) phoneFieldLayout.getComponentAt(0);
        phoneFields.remove(phoneField);
        phoneListLayout.remove(phoneFieldLayout);
    }
    private void removeEmailField(HorizontalLayout emailFieldLayout) {
        TextField emailField = (TextField) emailFieldLayout.getComponentAt(0);
        emailFields.remove(emailField);
        emailListLayout.remove(emailFieldLayout);
    }
    private void removeClientsComboBox(HorizontalLayout clientsComboBoxLayout) {
        ComboBox<String> clientsComboBox = (ComboBox<String>) clientsComboBoxLayout.getComponentAt(0);
        clientsComboBoxes.remove(clientsComboBox);
        clientsListLayout.remove(clientsComboBoxLayout);
    }
    private void removeSocialMediaField(HorizontalLayout socialMediaFieldLayout) {
        ComboBox<String> socialMediaComboBox = (ComboBox<String>) socialMediaFieldLayout.getComponentAt(0);
        TextField socialMediaUsernameField = (TextField) socialMediaFieldLayout.getComponentAt(1);
        socialMediaComboBoxes.remove(socialMediaComboBox);
        socialMediaUsernameFields.remove(socialMediaUsernameField);
        socialMediaListLayout.remove(socialMediaFieldLayout);
    }
    private String getPhoneNumbers() {
        StringBuilder phoneNumbers = new StringBuilder();
        for (TextField phoneField : phoneFields) {
            if (phoneField.isEmpty()) {
                continue;
            }
            phoneNumbers.append(phoneField.getValue()).append(", ");
        }
        return phoneNumbers.length() > 0 ? phoneNumbers.substring(0, phoneNumbers.length() - 2) : "";
    }
    private String getEmails() {
        StringBuilder emails = new StringBuilder();
        for (TextField emailField : emailFields) {
            if (emailField.isEmpty()) {
                continue;
            }
            emails.append(emailField.getValue()).append(", ");
        }
        return emails.length() > 0 ? emails.substring(0, emails.length() - 2) : "";
    }
    private String getClients() {
        StringBuilder clients = new StringBuilder();
        for (ComboBox<String> clientsComboBox : clientsComboBoxes) {
            if (clientsComboBox.isEmpty()) {
                continue;
            }
            clients.append(clientsComboBox.getValue()).append(", ");
        }
        return clients.length() > 0 ? clients.substring(0, clients.length() - 2) : "";
    }
    private String getSocialMedia() {
        StringBuilder socialMedia = new StringBuilder();
        for (int i = 0; i < socialMediaComboBoxes.size(); i++) {
            //if the username field is empty, then skip this iteration
            if (socialMediaUsernameFields.get(i).isEmpty()) {
                continue;
            }
            //if the social media combobox is empty, then replace it with "Otro"
            if (socialMediaComboBoxes.get(i).isEmpty()) {
                socialMedia.append("Otro").append("|").append(socialMediaUsernameFields.get(i).getValue()).append(", ");
                continue;
            }
            socialMedia.append(socialMediaComboBoxes.get(i).getValue()).append("|").append(socialMediaUsernameFields.get(i).getValue()).append(", ");
        }
        return socialMedia.length() > 0 ? socialMedia.substring(0, socialMedia.length() - 2) : "";
    }
    private void saveData(){
        String backend = "https://springbackend-production.up.railway.app";
        String url = backend + "/add";

        int id = 0;
        String name = this.name.getValue();
        name = name.replaceAll(";", "");
        String completeName = this.completeName.getValue();
        completeName = completeName.replaceAll(";", "");
        String phoneNumbers = getPhoneNumbers();
        String emails = getEmails();
        String company = this.company.getValue().toString();
        company = company.substring(1, company.length() - 1);
        String media = this.media.getValue().toString();
        media = media.substring(1, media.length() - 1);
        String sector = this.sector.getValue().toString();
        sector = sector.substring(1, sector.length() - 1);
        String position = this.position.getValue().toString();
        position = position.substring(1, position.length() - 1);
        String personalAddress = this.personalAddress.getValue();
        personalAddress = personalAddress.replaceAll(";", "");
        String professionalAddress = this.professionalAddress.getValue();
        professionalAddress = professionalAddress.replaceAll(";", "");
        String country = this.country.getValue();
        if (country==null) {country = "";}
        String community = this.community.getValue();
        if (community==null) {community = "";}
        String region = this.region.getValue();
        if (region==null) {region = "";}
        String Observations = this.Observations.getValue();
        Observations = Observations.replaceAll("\n", " ");
        Observations = Observations.replaceAll(";", "");
        String socialMedia = getSocialMedia();
        String clients = getClients();


        //if there is no phone or email, then show a notification and return
        if (phoneNumbers.isEmpty() && emails.isEmpty()) {
            Notification notification = new Notification();
            notification.setDuration(1000);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setText("Debe introducir al menos un teléfono o un email");
            notification.open();
            return;
        }
        //if there is a nonvalid email, then show a notification and return
        if (!emails.isEmpty() && !emails.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(, [a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})*$")) {
            Notification notification = new Notification();
            notification.setDuration(1000);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setText("Email no válido");
            notification.open();
            return;
        }
        //if there is a nonvalid phone number, then show a notification and retur
        //the phone has to match this pattern "^\\+?(\\d{1,3})?\\s*\\(?(\\d{1,4})\\)?[\\s.-]*\\d{1,4}[\\s.-]*\\d{1,4}[\\s.-]*\\d{1,9}$"
        if (!phoneNumbers.isEmpty() && !phoneNumbers.matches("^\\+?(\\d{1,3})?\\s*\\(?(\\d{1,4})\\)?[\\s.-]*\\d{1,4}[\\s.-]*\\d{1,4}[\\s.-]*\\d{1,9}(, \\+?(\\d{1,3})?\\s*\\(?(\\d{1,4})\\)?[\\s.-]*\\d{1,4}[\\s.-]*\\d{1,4}[\\s.-]*\\d{1,9})*$")) {
            Notification notification = new Notification();
            notification.setDuration(1000);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setText("Teléfono no válido");
            notification.open();
    
            return;
        }
        //the phone has to have a length between 5 and 18
        //for each phone number, check if it has a length between 5 and 18
        for (String phoneNumber : phoneNumbers.split(", ")) {
            if (!phoneNumbers.isEmpty() && (phoneNumber.length() < 5 || phoneNumber.length() > 18)) {
                Notification notification = new Notification();
                notification.setDuration(1000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setText("Teléfono no válido");
                notification.open();
                return;
            }
        }

        //create a new Data object from the values of the fields
        Data data = new Data(id, name, completeName, phoneNumbers, emails, company, media, sector, position, personalAddress, professionalAddress, country, community, region, Observations, socialMedia, clients);        

        //convert the data object to a json string
        Gson gson = new Gson();
        String json = gson.toJson(data);

        HttpResponse response = null;

        //send the json string to the backend
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();

        //send the request
        try {
            response = java.net.http.HttpClient.newHttpClient().send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
            //see if the response is a 200 OK
        if (response.statusCode() == 200) {
            //if it is, then show a notification that the data was saved
            Notification notification = new Notification();
            notification.setDuration(1000);
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            notification.setText("Datos guardados");
            notification.open();

            //reset the form
            resetForm();

        } else {
            //if it is not, then show a notification that the data was not saved
            Notification notification = new Notification();
            notification.setDuration(3000);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setText("Datos no guardados, error de conexión");
            notification.open();
        }
        } catch (Exception e) {
            Notification notification = new Notification();
            notification.setDuration(3000);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setText("Datos no guardados, error de conexión");
            notification.open();
        }

    }
    private void modifyData(){
        String backend = "https://springbackend-production.up.railway.app";
        String url = backend + "/modify";

        if (globalId == 0) {
            Notification notification = new Notification();
            notification.setDuration(1000);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setText("Debe seleccionar un contacto");
            notification.open();
            return;
        }

        int id = globalId;
        System.out.println("id: " + id);
        String name = this.name.getValue();
        name = name.replaceAll(";", "");
        String completeName = this.completeName.getValue();
        completeName = completeName.replaceAll(";", "");
        String phoneNumbers = getPhoneNumbers();
        String emails = getEmails();
        String company = this.company.getValue().toString();
        company = company.substring(1, company.length() - 1);
        String media = this.media.getValue().toString();
        media = media.substring(1, media.length() - 1);
        String sector = this.sector.getValue().toString();
        sector = sector.substring(1, sector.length() - 1);
        String position = this.position.getValue().toString();
        position = position.substring(1, position.length() - 1);
        String personalAddress = this.personalAddress.getValue();
        personalAddress = personalAddress.replaceAll(";", "");
        String professionalAddress = this.professionalAddress.getValue();
        professionalAddress = professionalAddress.replaceAll(";", "");
        String country = this.country.getValue();
        if (country==null) {country = "";}
        String community = this.community.getValue();
        if (community==null) {community = "";}
        String region = this.region.getValue();
        if (region==null) {region = "";}
        String Observations = this.Observations.getValue();
        Observations = Observations.replaceAll("\n", " ");
        Observations = Observations.replaceAll(";", "");
        String socialMedia = getSocialMedia();
        String clients = getClients();

        //if there is no phone or email, then show a notification and return
        if (phoneNumbers.isEmpty() && emails.isEmpty()) {
            Notification notification = new Notification();
            notification.setDuration(1000);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setText("Debe introducir al menos un teléfono o un email");
            notification.open();
            return;
        }
        //if there is a nonvalid email, then show a notification and return
        if (!emails.isEmpty() && !emails.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(, [a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})*$")) {
            Notification notification = new Notification();
            notification.setDuration(1000);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setText("Email no válido");
            notification.open();
            return;
        }
        //if there is a nonvalid phone number, then show a notification and retur
        //the phone has to match this pattern "^\\+?(\\d{1,3})?\\s*\\(?(\\d{1,4})\\)?[\\s.-]*\\d{1,4}[\\s.-]*\\d{1,4}[\\s.-]*\\d{1,9}$"
        if (!phoneNumbers.isEmpty() && !phoneNumbers.matches("^\\+?(\\d{1,3})?\\s*\\(?(\\d{1,4})\\)?[\\s.-]*\\d{1,4}[\\s.-]*\\d{1,4}[\\s.-]*\\d{1,9}(, \\+?(\\d{1,3})?\\s*\\(?(\\d{1,4})\\)?[\\s.-]*\\d{1,4}[\\s.-]*\\d{1,4}[\\s.-]*\\d{1,9})*$")) {
            Notification notification = new Notification();
            notification.setDuration(1000);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setText("Teléfono no válido");
            notification.open();
    
            return;
        }
        //the phone has to have a length between 5 and 18
        //for each phone number, check if it has a length between 5 and 18
        for (String phoneNumber : phoneNumbers.split(", ")) {
            if (!phoneNumbers.isEmpty() &&(phoneNumber.length() < 5 || phoneNumber.length() > 18)) {
                Notification notification = new Notification();
                notification.setDuration(1000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setText("Teléfono no válido");
                notification.open();
                return;
            }
        }

        Data data = new Data(id, name, completeName, phoneNumbers, emails, company, media, sector, position, personalAddress, professionalAddress, country, community, region, Observations, socialMedia, clients);
        System.out.println(data.getId());

        Gson gson = new Gson();
        String json = gson.toJson(data);

        HttpResponse response = null;

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(json))
            .build();

        try {
            response = java.net.http.HttpClient.newHttpClient().send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                Notification notification = new Notification();
                notification.setDuration(1000);
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                notification.setText("Datos modificados");
                notification.open();

                resetForm();

            } else {
                Notification notification = new Notification();
                notification.setDuration(3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setText("Datos no modificados, error de conexión");
                notification.open();
            }
        } catch (Exception e) {
            Notification notification = new Notification();
            notification.setDuration(3000);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setText("Datos no modificados, error de conexión");
            notification.open();
        }
    }
    private void deleteData(){
        String backend = "https://springbackend-production.up.railway.app";
        String url = backend + "/delete";

        if (globalId == 0) {
            Notification notification = new Notification();
            notification.setDuration(1000);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setText("Debe seleccionar un contacto");
            notification.open();
            return;
        }

        int id = globalId;
        System.out.println("id: " + id);

        // Send the id to the backend
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.setId(id);

        Gson gson = new Gson();
        String json = gson.toJson(deleteRequest);

        HttpResponse<String> response = null;
        
        // POST request
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();
        
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                Notification notification = new Notification();
                notification.setDuration(1000);
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                notification.setText("Datos eliminados");
                notification.open();

                resetForm();

            } else {
                Notification notification = new Notification();
                notification.setDuration(3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setText("Datos no eliminados, error de conexión");
                notification.open();
            }
        } catch (Exception e) {
            Notification notification = new Notification();
            notification.setDuration(3000);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            notification.setText("Datos no eliminados, error de conexión");
            notification.open();
        }
    }
    
    private void resetForm(){
        globalId = 0;
        name.clear();
        completeName.clear();
        phoneFields.forEach(TextField::clear);
        emailFields.forEach(TextField::clear);
        company.clear();
        media.clear();
        sector.clear();
        position.clear();
        personalAddress.clear();
        professionalAddress.clear();
        country.clear();
        community.clear();
        region.clear();
        Observations.clear();
        socialMediaComboBoxes.forEach(ComboBox::clear);
        socialMediaUsernameFields.forEach(TextField::clear);
        clientsComboBoxes.forEach(ComboBox::clear);
        getAllButton.setVisible(true);
        modifyButton.setVisible(false);
        cancelButton.setVisible(false);
        deleteButton.setVisible(false);

        //leave only one phone field
        while (phoneFields.size() > 1) {
            //remove the last phone field passing the horizontal layout that contains it
            removePhoneField((HorizontalLayout) phoneFields.get(phoneFields.size() - 1).getParent().get());
        }
        //leave only one email field
        
        while (emailFields.size() > 1) {
            //remove the last email field passing the horizontal layout that contains it
            removeEmailField((HorizontalLayout) emailFields.get(emailFields.size() - 1).getParent().get());
        }

        //leave only one social media field
        while (socialMediaComboBoxes.size() > 1) {
            //remove the last social media field passing the horizontal layout that contains it
            removeSocialMediaField((HorizontalLayout) socialMediaComboBoxes.get(socialMediaComboBoxes.size() - 1).getParent().get());
        }
        //leave only one clients field
        while (clientsComboBoxes.size() > 1) {
            //remove the last clients field passing the horizontal layout that contains it
            removeClientsComboBox((HorizontalLayout) clientsComboBoxes.get(clientsComboBoxes.size() - 1).getParent().get());
        }
    }



} 