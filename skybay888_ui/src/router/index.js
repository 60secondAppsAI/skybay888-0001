import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Flights from  '@/pages/Flights.vue';
import FlightDetail from  '@/pages/FlightDetail.vue';
import Airlines from  '@/pages/Airlines.vue';
import AirlineDetail from  '@/pages/AirlineDetail.vue';
import Airports from  '@/pages/Airports.vue';
import AirportDetail from  '@/pages/AirportDetail.vue';
import Passengers from  '@/pages/Passengers.vue';
import PassengerDetail from  '@/pages/PassengerDetail.vue';
import Tickets from  '@/pages/Tickets.vue';
import TicketDetail from  '@/pages/TicketDetail.vue';
import Bookings from  '@/pages/Bookings.vue';
import BookingDetail from  '@/pages/BookingDetail.vue';
import Seats from  '@/pages/Seats.vue';
import SeatDetail from  '@/pages/SeatDetail.vue';
import Baggages from  '@/pages/Baggages.vue';
import BaggageDetail from  '@/pages/BaggageDetail.vue';
import PaymentDetails from  '@/pages/PaymentDetails.vue';
import PaymentDetailDetail from  '@/pages/PaymentDetailDetail.vue';
import Aircrafts from  '@/pages/Aircrafts.vue';
import AircraftDetail from  '@/pages/AircraftDetail.vue';
import Crews from  '@/pages/Crews.vue';
import CrewDetail from  '@/pages/CrewDetail.vue';
import RouteMaps from  '@/pages/RouteMaps.vue';
import RouteMapDetail from  '@/pages/RouteMapDetail.vue';
import Meals from  '@/pages/Meals.vue';
import MealDetail from  '@/pages/MealDetail.vue';
import CheckIns from  '@/pages/CheckIns.vue';
import CheckInDetail from  '@/pages/CheckInDetail.vue';
import FlightStatuss from  '@/pages/FlightStatuss.vue';
import FlightStatusDetail from  '@/pages/FlightStatusDetail.vue';
import Loungess from  '@/pages/Loungess.vue';
import LoungesDetail from  '@/pages/LoungesDetail.vue';
import Delayss from  '@/pages/Delayss.vue';
import DelaysDetail from  '@/pages/DelaysDetail.vue';
import TicketPrices from  '@/pages/TicketPrices.vue';
import TicketPriceDetail from  '@/pages/TicketPriceDetail.vue';
import CustomerServiceRequests from  '@/pages/CustomerServiceRequests.vue';
import CustomerServiceRequestDetail from  '@/pages/CustomerServiceRequestDetail.vue';
import Promotionss from  '@/pages/Promotionss.vue';
import PromotionsDetail from  '@/pages/PromotionsDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/flights',
																				  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/flights',
		name: 'Flights',
		layout: DefaultLayout,
		component: Flights,
	},
	{
	    path: '/flight/:flightId', 
	    name: 'FlightDetail',
		layout: DefaultLayout,
	    component: FlightDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airlines',
		name: 'Airlines',
		layout: DefaultLayout,
		component: Airlines,
	},
	{
	    path: '/airline/:airlineId', 
	    name: 'AirlineDetail',
		layout: DefaultLayout,
	    component: AirlineDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airports',
		name: 'Airports',
		layout: DefaultLayout,
		component: Airports,
	},
	{
	    path: '/airport/:airportId', 
	    name: 'AirportDetail',
		layout: DefaultLayout,
	    component: AirportDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/passengers',
		name: 'Passengers',
		layout: DefaultLayout,
		component: Passengers,
	},
	{
	    path: '/passenger/:passengerId', 
	    name: 'PassengerDetail',
		layout: DefaultLayout,
	    component: PassengerDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/tickets',
		name: 'Tickets',
		layout: DefaultLayout,
		component: Tickets,
	},
	{
	    path: '/ticket/:ticketId', 
	    name: 'TicketDetail',
		layout: DefaultLayout,
	    component: TicketDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/bookings',
		name: 'Bookings',
		layout: DefaultLayout,
		component: Bookings,
	},
	{
	    path: '/booking/:bookingId', 
	    name: 'BookingDetail',
		layout: DefaultLayout,
	    component: BookingDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/seats',
		name: 'Seats',
		layout: DefaultLayout,
		component: Seats,
	},
	{
	    path: '/seat/:seatId', 
	    name: 'SeatDetail',
		layout: DefaultLayout,
	    component: SeatDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/baggages',
		name: 'Baggages',
		layout: DefaultLayout,
		component: Baggages,
	},
	{
	    path: '/baggage/:baggageId', 
	    name: 'BaggageDetail',
		layout: DefaultLayout,
	    component: BaggageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/paymentDetails',
		name: 'PaymentDetails',
		layout: DefaultLayout,
		component: PaymentDetails,
	},
	{
	    path: '/paymentDetail/:paymentDetailId', 
	    name: 'PaymentDetailDetail',
		layout: DefaultLayout,
	    component: PaymentDetailDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/aircrafts',
		name: 'Aircrafts',
		layout: DefaultLayout,
		component: Aircrafts,
	},
	{
	    path: '/aircraft/:aircraftId', 
	    name: 'AircraftDetail',
		layout: DefaultLayout,
	    component: AircraftDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/crews',
		name: 'Crews',
		layout: DefaultLayout,
		component: Crews,
	},
	{
	    path: '/crew/:crewId', 
	    name: 'CrewDetail',
		layout: DefaultLayout,
	    component: CrewDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/routeMaps',
		name: 'RouteMaps',
		layout: DefaultLayout,
		component: RouteMaps,
	},
	{
	    path: '/routeMap/:routeMapId', 
	    name: 'RouteMapDetail',
		layout: DefaultLayout,
	    component: RouteMapDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/meals',
		name: 'Meals',
		layout: DefaultLayout,
		component: Meals,
	},
	{
	    path: '/meal/:mealId', 
	    name: 'MealDetail',
		layout: DefaultLayout,
	    component: MealDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/checkIns',
		name: 'CheckIns',
		layout: DefaultLayout,
		component: CheckIns,
	},
	{
	    path: '/checkIn/:checkInId', 
	    name: 'CheckInDetail',
		layout: DefaultLayout,
	    component: CheckInDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/flightStatuss',
		name: 'FlightStatuss',
		layout: DefaultLayout,
		component: FlightStatuss,
	},
	{
	    path: '/flightStatus/:flightStatusId', 
	    name: 'FlightStatusDetail',
		layout: DefaultLayout,
	    component: FlightStatusDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/loungess',
		name: 'Loungess',
		layout: DefaultLayout,
		component: Loungess,
	},
	{
	    path: '/lounges/:loungesId', 
	    name: 'LoungesDetail',
		layout: DefaultLayout,
	    component: LoungesDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/delayss',
		name: 'Delayss',
		layout: DefaultLayout,
		component: Delayss,
	},
	{
	    path: '/delays/:delaysId', 
	    name: 'DelaysDetail',
		layout: DefaultLayout,
	    component: DelaysDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/ticketPrices',
		name: 'TicketPrices',
		layout: DefaultLayout,
		component: TicketPrices,
	},
	{
	    path: '/ticketPrice/:ticketPriceId', 
	    name: 'TicketPriceDetail',
		layout: DefaultLayout,
	    component: TicketPriceDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/customerServiceRequests',
		name: 'CustomerServiceRequests',
		layout: DefaultLayout,
		component: CustomerServiceRequests,
	},
	{
	    path: '/customerServiceRequest/:customerServiceRequestId', 
	    name: 'CustomerServiceRequestDetail',
		layout: DefaultLayout,
	    component: CustomerServiceRequestDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/promotionss',
		name: 'Promotionss',
		layout: DefaultLayout,
		component: Promotionss,
	},
	{
	    path: '/promotions/:promotionsId', 
	    name: 'PromotionsDetail',
		layout: DefaultLayout,
	    component: PromotionsDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
