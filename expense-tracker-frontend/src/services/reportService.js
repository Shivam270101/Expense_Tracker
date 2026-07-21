import api from "./api";

/**
 * Generate Report
 */
export const generateReport = async (reportRequest) => {

    const response = await api.post(
        "/reports",
        reportRequest
    );

    return response.data;

};

/**
 * Export Report as PDF
 */
export const exportPdf = async (reportRequest) => {

    const response = await api.post(
        "/reports/export/pdf",
        reportRequest,
        {
            responseType: "blob"
        }
    );

    return response.data;

};

/**
 * Export Report as Excel
 */
export const exportExcel = async (reportRequest) => {

    const response = await api.post(
        "/reports/export/excel",
        reportRequest,
        {
            responseType: "blob"
        }
    );

    return response.data;

};