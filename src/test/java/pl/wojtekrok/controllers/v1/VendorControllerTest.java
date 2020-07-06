package pl.wojtekrok.controllers.v1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.wojtekrok.api.v1.model.VendorDTO;
import pl.wojtekrok.api.v1.model.VendorListDTO;
import pl.wojtekrok.domain.Vendor;
import pl.wojtekrok.services.VendorService;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = VendorController.class)
class VendorControllerTest extends AbstractRestControllerTest{

    @MockBean //provided by Spring Context
    VendorService vendorService;

    @Autowired //provided by Spring Context
    MockMvc mockMvc;

    VendorDTO vendorDTO1;
    VendorDTO vendorDTO2;

    @BeforeEach
    void setUp() {
        vendorDTO1 = new VendorDTO("Vendor 1", VendorController.BASE_URL + "/1");
        vendorDTO2 = new VendorDTO("Vendor 2", VendorController.BASE_URL + "/2");
    }

    @Test
    void getVendorList() throws Exception {
        VendorListDTO vendorListDTO = new VendorListDTO(Arrays.asList(vendorDTO1, vendorDTO2));

        given(vendorService.getAllVendors()).willReturn(vendorListDTO);

        mockMvc.perform(get(VendorController.BASE_URL)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendorDTOS", hasSize(2)));
    }

    @Test
    void getVendorById() throws Exception {

        given(vendorService.getVendorById(anyLong())).willReturn(vendorDTO2);

        mockMvc.perform(get(VendorController.BASE_URL + "/2")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO2.getName())));
    }

    @Test
    void createNewVendor() throws Exception {

        given(vendorService.createNewVendor(vendorDTO1)).willReturn(vendorDTO1);

        mockMvc.perform(post(VendorController.BASE_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(vendorDTO1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO1.getName())));
    }

    @Test
    void updateVendor() throws Exception {

        given(vendorService.updateVendorByDTO(anyLong(), ArgumentMatchers.any(VendorDTO.class))).willReturn(vendorDTO2);

        mockMvc.perform(put(VendorController.BASE_URL + "/2")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(vendorDTO2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO2.getName())));
    }

    @Test
    void patchVendor() throws Exception {

        given(vendorService.patchVendor(anyLong(), ArgumentMatchers.any(VendorDTO.class))).willReturn(vendorDTO1);

        mockMvc.perform(patch(VendorController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(vendorDTO1.getName())));
    }

    @Test
    void deleteVendor() throws Exception {
        mockMvc.perform(delete(VendorController.BASE_URL + "/1"))
                .andExpect(status().isOk());

        then(vendorService).should().deleteVendorById(anyLong());
    }
}