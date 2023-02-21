package com.example.mpagos.testShared

import com.example.mpagos.ui.main.domain.model.PaymentMethodElement
import org.mockito.kotlin.mock

var samplePaymentMethod = PaymentMethodElement(
    id = "",
    name = "Argencard",
    paymentTypeId = "credit_card",
    status = "active",
    secureThumbnail = "https://http2.mlstatic.com/storage/logos-api-admin/d7e55980-f3be-11eb-8e0d-6f4af49bf82e-xl@2x.png",
    thumbnail = "https://http2.mlstatic.com/storage/logos-api-admin/d7e55980-f3be-11eb-8e0d-6f4af49bf82e-xl@2x.png",
    deferredCapture = "supported",
    settings = mock(),
    additionalInfoNeeded = mock(),
    minAllowedAmount = 1,
    maxAllowedAmount = 1,
    accreditationTime = 1,
    financialInstitutions = mock(),
    processingModes = mock()
)