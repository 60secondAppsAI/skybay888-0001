<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <flightStatus-table
            v-if="flightStatuss && flightStatuss.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:flightStatuss="flightStatuss"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-flight-statuss="getAllFlightStatuss"
             >

            </flightStatus-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import FlightStatusTable from "@/components/FlightStatusTable";
import FlightStatusService from "../services/FlightStatusService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    FlightStatusTable,
  },
  data() {
    return {
      flightStatuss: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllFlightStatuss(sortBy='flightStatusId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await FlightStatusService.getAllFlightStatuss(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.flightStatuss.length) {
					this.flightStatuss = response.data.flightStatuss;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching flightStatuss:", error);
        }
        
      } catch (error) {
        console.error("Error fetching flightStatus details:", error);
      }
    },
  },
  mounted() {
    this.getAllFlightStatuss();
  },
  created() {
    this.$root.$on('searchQueryForFlightStatussChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllFlightStatuss();
    })
  }
};
</script>
<style></style>
