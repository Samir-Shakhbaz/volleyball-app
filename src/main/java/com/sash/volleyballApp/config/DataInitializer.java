package com.sash.volleyballApp.config;

import com.sash.volleyballApp.models.Facility;
import com.sash.volleyballApp.repositories.FacilityRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Autowired
    private FacilityRepository facilityRepository;

    @PostConstruct
    public void initializeData() {
        if (facilityRepository.count() == 0) {
            facilityRepository.save(new Facility(null, "The Sandbox at VBGB",
                    "920 Hamilton St, Charlotte, NC 28206", "Sand", 5, "Sportslink",
                    "https://sportslinkus.com/court-rentals", "Required"));

            facilityRepository.save(new Facility(null, "Thirsty Social Club",
                    "508 Woodlawn St, Belmont, NC 28206", "Sand", 4, "Sportslink",
                    "https://sportslinkus.com/court-rentals", "Required"));

            facilityRepository.save(new Facility(null, "Small Bar",
                    "3415 Sc-51, Fort Mill, SC 29715", "Sand", 3, "LKN Crew, Charlotte Beach Juniors Volleyball",
                    "https://www.facebook.com/lkn.crew.14", "None"));

            facilityRepository.save(new Facility(null, "Sports Connection - Granite Street",
                    "10930 Granite St, Charlotte, NC 28273", "Indoor", 8, "Sports Connection, CJV (Carolina Juniors Volleyball), Carolina Beach Volleyball Academy",
                    "https://sportsconnectionnc.com/sports-rentals/volleyball-open-play/", "Required"));

            facilityRepository.save(new Facility(null, "Sports Connection - Northlake",
                    "8626 Hankins Road, Charlotte, NC 28269", "Indoor", 2, "Sports Connection, CJV (Carolina Juniors Volleyball), Carolina Beach Volleyball Academy, CETS, Pyro Volleyball, LKN Crew",
                    "https://sportsconnectionnc.com/sports-rentals/volleyball-open-play/", "Required"));

            facilityRepository.save(new Facility(null, "Blythe Landing Park",
                    "8000 Blythe Landing Rd, Charlotte, NC 28273", "Sand", 4, "Mecklenburg County Park and Recreation",
                    "https://www.visitlakenorman.org/listing/blythe-landing/177/", "Optional"));

            facilityRepository.save(new Facility(null, "Jeff Adams Tennis Center",
                    "1200 W Tyvola Rd, Charlotte, NC 28217", "Indoor", 9, "Mecklenburg County Park and Recreation",
                    "https://parkandrec.mecknc.gov/Activities/tennis-pickleball-volleyball", "Optional"));

            facilityRepository.save(new Facility(null, "Renaissance Park",
                    "1200 W Tyvola Rd, Charlotte, NC 28217", "Sand", 7, "Mecklenburg County Park and Recreation, Sportslink",
                    "https://parkandrec.mecknc.gov/Activities/tennis-pickleball-volleyball", "Optional"));

            facilityRepository.save(new Facility(null, "Renaissance Park",
                    "1200 W Tyvola Rd, Charlotte, NC 28217", "Grass", 7, "Mecklenburg County Park and Recreation, Kumquats Social",
                    "https://parkandrec.mecknc.gov/Activities/tennis-pickleball-volleyball", "Optional"));

            facilityRepository.save(new Facility(null, "Freedom Park",
                    "1900 East Blvd, Charlotte, NC 28203", "Sand", 2, "Mecklenburg County Park and Recreation",
                    "https://www.charlottesgotalot.com/things-to-do/outdoors-adventure/freedom-park", "Optional"));

            facilityRepository.save(new Facility(null, "Freedom Park",
                    "1900 East Blvd, Charlotte, NC 28203", "Grass", 24, "Mecklenburg County Park and Recreation",
                    "https://www.charlottesgotalot.com/things-to-do/outdoors-adventure/freedom-park", "Optional"));

            facilityRepository.save(new Facility(null, "Clarks Creek Park",
                    "5435 Hucks Rd, Charlotte, NC 28269", "Grass", 4, "Mecklenburg County Park and Recreation",
                    "https://www.yelp.ca/biz/clarks-creek-community-park-charlotte", "Optional"));

            facilityRepository.save(new Facility(null, "Park Road Park",
                    "6220 Park Rd, Charlotte, NC 28210", "Sand", 1, "Mecklenburg County Park and Recreation",
                    "https://www.yelp.com/biz/park-road-park-charlotte?osq=volleyball+courts&q=volleyball", "None"));

            facilityRepository.save(new Facility(null, "Hornets Nest Park",
                    "6301 Beatties Ford Rd, Charlotte, NC 28216", "Sand", 2, "Mecklenburg County Park and Recreation",
                    "https://www.yelp.com/biz/hornets-nest-park-charlotte?osq=volleyball+courts", "None"));

            facilityRepository.save(new Facility(null, "Clanton Park",
                    "1520 Clanton Rd, Charlotte, NC 28208", "Grass", 4, "Mecklenburg County Park and Recreation",
                    "https://parkandrec.mecknc.gov/current-projects/park-projects/clanton-park-improvements", "Optional"));

            facilityRepository.save(new Facility(null, "MLK Park",
                    "950W 5th St, Charlotte, NC 28202", "Grass", 4, "Mecklenburg County Park and Recreation",
                    "https://www.yelp.com/biz/martin-luther-king-park-charlotte", "Optional"));

            facilityRepository.save(new Facility(null, "George Poston Park",
                    "1101 Lowell Spencer Mountain Rd, Gastonia, NC 28056", "Sand", 1, "Gaston County Parks and Recreation",
                    "https://www.gastongov.com/facilities/facility/details/georgepostonpark-6", "None"));

            facilityRepository.save(new Facility(null, "Latta Park",
                    "601 East Park Ave, Charlotte, NC 28203", "Grass", 4, "Mecklenburg County Park and Recreation",
                    "https://www.charlottesgotalot.com/things-to-do/outdoors-adventure/latta-park", "Optional"));

            facilityRepository.save(new Facility(null, "Tuckaseegee Park",
                    "4820 Tuckaseegee Rd, Charlotte, NC 28208", "Grass", 4, "Mecklenburg County Park and Recreation",
                    "https://parkandrec.mecknc.gov/Places-to-Visit/Rec-Centers/tuckaseegee-recreation-center", "Optional"));

            facilityRepository.save(new Facility(null, "CUVC (Carolina Union Volleyball Club)",
                    "11100 Johnnycake Rd, Charlotte, NC 28277", "Indoor", 6, "CUVC (Carolina Union Volleyball Club), Sportslink, LKN Crew",
                    "https://carolinauvc.com/club/facility-information", "Required"));

            facilityRepository.save(new Facility(null, "First Baptist Church of Charlotte",
                    "301 S Davidson St, Charlotte, NC 28202", "Indoor", 1, "First Baptist Church of Charlotte",
                    "https://charlottefbc.org/youngadults", "None"));

            facilityRepository.save(new Facility(null, "Eastway Regional Recreation Center",
                    "3150 Eastway Park Dr, Charlotte, NC 28213", "Indoor", 6, "Mecklenburg County Park and Recreation",
                    "https://maps.app.goo.gl/24yoqcw8TttepmBKA", "Required"));

            facilityRepository.save(new Facility(null, "IBRC (Ivory Baker Recreation Center)",
                    "1920 Stroud Park Court, Charlotte, NC 28206", "Indoor", 1, "Mecklenburg County Park and Recreation",
                    "https://apm.activecommunities.com/mecklenburgparks/Facility_Search?facility_id=148&go_legacy_facility_detail=true", "Required"));

            facilityRepository.save(new Facility(null, "Sportsplex at Matthews",
                    "5841 Brookshire Blvd, Charlotte, NC 28216", "Grass", 60, "Mecklenburg County Park and Recreation",
                    "https://maps.app.goo.gl/TrtNkRBj9mWVLBdv6", "Required"));

            facilityRepository.save(new Facility(null, "Southside Park",
                    "2645 Toomey Ave, Charlotte, NC 28203", "Grass", 24, "Mecklenburg County Park and Recreation, CAAVL (Charlotte Area Architect's Volleyball League)",
                    "https://maps.app.goo.gl/hRgk57sHK6Tvbxk18", "Optional"));

            facilityRepository.save(new Facility(null, "Veterans Park",
                    "2136 Central Ave, Charlotte, NC 28205", "Grass", 18, "Mecklenburg County Park and Recreation, CAAVL (Charlotte Area Architect's Volleyball League), Kumquats Social",
                    "https://maps.app.goo.gl/meDksZCkK8F1twmm7", "Optional"));

            facilityRepository.save(new Facility(null, "Independence Park",
                    "300 Hawthorne Ln, Charlotte, NC 28204", "Grass", 4, "Mecklenburg County Park and Recreation, Kumquats Social",
                    "https://maps.app.goo.gl/4KFhHaWG2QmR3Kzx8", "Optional"));

            facilityRepository.save(new Facility(null, "Chestnut Square Park",
                    "320 Chestnut Pkwy, Indian Trail, NC 28079", "Sand", 4, "Indian Trail Town Parks and Facilities",
                    "https://www.indiantrail.org/Facilities/Facility/Details/Chestnut-Square-Park-1", "Optional"));

            facilityRepository.save(new Facility(null, "Carolina Courts - Concord",
                    "24 Spring St. SW, Concord, NC 28025", "Indoor", 8, "Carolina Courts",
                    "https://www.carolinacourts.com/sevavolleyball", "Required"));

            facilityRepository.save(new Facility(null, "Carolina Courts - Indian Trail",
                    "240 Chestnut Pkwy, Indian Trail, NC 28079", "Indoor", 8, "Carolina Courts, SEVA (South Eastern Volleyball Academy)",
                    "https://www.carolinacourts.com/sevavolleyball", "Required"));

            facilityRepository.save(new Facility(null, "Saeed's Bar and Grill",
                    "20832 Catawba Ave\nCornelius, NC 28031", "Sand", 2, "Saeed's Volleyball, GO Charlotte",
                    "https://saeedsvolleyball.com/", "None"));

            facilityRepository.save(new Facility(null, "Rama Swim Club",
                    "6109 Wheeler Dr, Charlotte, NC 28211", "Sand", 2, "Rama Swim Club",
                    "https://ramaswimclub.com/volleyball-16/", "Optional"));

            facilityRepository.save(new Facility(null, "Mako Beach Volleyball Club",
                    "435 Mazeppa Rd, Mooresville, NC 28115", "Sand", 4, "Mako Beach Volleyball Club",
                    "https://makobeach.com/", "Required"));

            facilityRepository.save(new Facility(null, "Northern Regional Recreation Center",
                    "18121 Old Statesville Rd, Cornelius, NC 28031", "Indoor", 2, "Mecklenburg County Park and Recreation",
                    "https://parkandrec.mecknc.gov/places-to-visit/rec-centers/northern-regional-recreation-center", "Required"));

            facilityRepository.save(new Facility(null, "Huntersville Rec Center",
                    "11836 Verhoeff Dr, Huntersville, NC 28078", "Indoor", 2, "Huntersville Park and Recreation",
                    "https://www.huntersville.org/3073/Parks-Recreation-Department", "Required"));

            facilityRepository.save(new Facility(null, "Rock Hill Sports & Events Center",
                    "326 Technology Center Way, Rock Hill, SC 29730", "Indoor", 16, "York County Park and Recreation",
                    "https://www.visityorkcounty.com/things-to-do/sports-recreation/", "Required"));

            facilityRepository.save(new Facility(null, "Mallard Creek Park Soccer Fields",
                    "3001 Johnston Oehler Rd, Charlotte, NC 28269", "Grass", 24, "Mecklenburg County Park and Recreation, Pyro Volleyball",
                    "https://www.ayso605.com/Default.aspx?tabid=850442", "Optional"));

            facilityRepository.save(new Facility(null, "Mallard Creek Park Volleyball Sand Court",
                    "3001 Johnston Oehler Rd, Charlotte, NC 28269", "Sand", 1, "Mecklenburg County Park and Recreation",
                    "https://maps.app.goo.gl/izPVV3LoTdfzWxBX9", "None"));

            facilityRepository.save(new Facility(null, "Hickory Grove Recreation Center",
                    "6709 Pence Rd, Charlotte, NC 28215", "Indoor", 2, "Mecklenburg County Park and Recreation",
                    "https://parkandrec.mecknc.gov/Places-to-Visit/Rec-Centers/hickory-grove-recreation-center", "Required"));

            facilityRepository.save(new Facility(null, "JWU Wildcat Center",
                    "235 S Cedar St, Charlotte, NC 28202", "Indoor", 0, "JWU (Johnson & Wales University)",
                    "https://charlotte.jwuathletics.com/index.aspx", "Required"));

            facilityRepository.save(new Facility(null, "UNC Charlotte Belk Gym",
                    "8911 University Road, Charlotte, NC 28262", "Indoor", 2, "UNC Charlotte University",
                    "https://urec.charlotte.edu/belk-gym", "None"));

            facilityRepository.save(new Facility(null, "UREC (UNC Charlotte University Recreation Center)",
                    "8827 Craver Rd, Charlotte, NC 28262", "Indoor", 2, "UNC Charlotte University",
                    "https://urec.charlotte.edu/open-recreation", "Required"));

            facilityRepository.save(new Facility(null, "UREC (UNC Charlotte University Recreation Center)",
                    "8827 Craver Rd, Charlotte, NC 28262", "Sand", 1, "UNC Charlotte University",
                    "https://urec.charlotte.edu/open-recreation", "Required"));

            facilityRepository.save(new Facility(null, "Anne Springs Close Greenway",
                    "2573 Lake Haigler Dr, Fort Mill, SC 29715", "Grass", 6, "LKN Crew",
                    "https://www.ascgreenway.org/visit/gatewaycanteen/", "Required"));

            facilityRepository.save(new Facility(null, "Frank Liske Park & Soccer Complex",
                    "4001 Stough Rd Concord, NC 28027", "Sand", 4, "Cabarrus County Parks & Recreation",
                    "https://www.cabarruscounty.us/Parks/Frank-Liske-Park", "Optional"));

            facilityRepository.save(new Facility(null, "William R. Davie Park",
                    "4635 Pineville-Matthews Rd, Charlotte, NC 28226", "Sand", 1, "Mecklenburg County Park and Recreation",
                    "https://www.yelp.com/biz/mecklenburg-county-william-r-davie-park-charlotte?osq=volleyball+courts&q=volleyball", "None"));
        }
    }
}
