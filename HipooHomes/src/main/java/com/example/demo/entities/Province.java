package com.example.demo.entities;

public enum Province {
	A_Coruña("A Coruña"),
	Álava("Álava"),
	Alicante("Alicante"),
	Almería("Almería"),
	Asturias("Asturias"),
	Ávila("Ávila"),
	Badajoz("Badajoz"),
	Baleares("Baleares"),
	Barcelona("Barcelona"),
	Burgos("Burgos"),
	Cáceres("Cáceres"),
	Cádiz("Cádiz"),
	Cantabria("Cantabria"),
	Castellón("Castellón"),
	Ciudad_Real("Ciudad Real"),
	Córdoba("Córdoba"),
	Cuenca("Cuenca"),
	Girona("Girona"),
	Granada("Granada"),
	Guadalajara("Guadalajara"),
	Gipuzkoa("Gipuzkoa"),
	Huelva("Huelva"),
	Huesca("Huesca"),
	Jaén("Jaén"),
	La_Rioja("La Rioja"),
	Las_Palmas("Las Palmas"),
	León("León"),
	Lérida("Lérida"),
	Lugo("Lugo"),
	Madrid("Madrid"),
	Málaga("Málaga"),
	Murcia("Murcia"),
	Navarra("Navarra"),
	Ourense("Ourense"),
	Palencia("Palencia"),
	Pontevedra("Pontevedra"),
	Salamanca("Salamanca"),
	Segovia("Segovia"),
	Sevilla("Sevilla"),
	Soria("Soria"),
	Tarragona("Tarragona"),
	Santa_Cruz_de_Tenerife("Santa Cruz de Tenerife"),
	Teruel("Teruel"),
	Toledo("Toledo"),
	Valencia("Valencia"),
	Valladolid("Valladolid"),
	Vizcaya("Vizcaya"),
	Zamora("Zamora"),
	Zaragosza("Zaragosza");
	
	private String text;
	 
    Province(String text) {
        this.text = text;
    }
 
    public String getText() {
        return text;
    }
}
