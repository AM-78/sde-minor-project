const exampleDocuments = [
    { 
        id: 1,
        title: "Project Plan",
        creationTime: Date.now() - 10000
    },
    {
        id: 2,
        title: "Design Document",
        creationTime: Date.now() - 50000,
    },
    {
        id: 3,
        title: "API Documentation",
        creationTime: Date.now() - 800000,
    },
    { 
        id: 4,
        title: "User Guide",
        creationTime: Date.now() - 150000
    },
];


let changes = [
    {
        "id": "0844196d-0222-4950-96ba-ae0b2841bd80",
        "docId": "a6a3e4b2-e58e-44f4-ac01-1d25c70223cf",
        "userId": "8e592557-b7e2-4105-8fcd-088538d9d8ea",
        "content": "[\"MCwxLDIyMSwyMTMsMTkxLDE3Myw5LDEsMCw0\"]",
        "version": 18,
        "creationTime": "2024-09-29T21:58:16"
    },
    {
        "id": "103f394d-90dd-4f50-aa28-db6a411504e8",
        "docId": "a6a3e4b2-e58e-44f4-ac01-1d25c70223cf",
        "userId": "8e592557-b7e2-4105-8fcd-088538d9d8ea",
        "content": "[\"MCwxLDIyMSwyMTMsMTkxLDE3Myw5LDEsMCw0\"]",
        "version": 16,
        "creationTime": "2024-09-29T21:44:18"
    },
    {
        "id": "1934e9db-d4f1-45ae-8b42-20988c663d28",
        "docId": "a6a3e4b2-e58e-44f4-ac01-1d25c70223cf",
        "userId": "8e592557-b7e2-4105-8fcd-088538d9d8ea",
        "content": "[\"MCwxLDIyMSwyMTMsMTkxLDE3Myw5LDEsMCw0\"]",
        "version": 17,
        "creationTime": "2024-09-29T21:54:00"
    },
    {
        "id": "a5fc8682-a644-4717-b036-53a9ca0a0505",
        "docId": "a6a3e4b2-e58e-44f4-ac01-1d25c70223cf",
        "userId": "8e592557-b7e2-4105-8fcd-088538d9d8ea",
        "content": "[\"MSwxLDIyMSwyMTMsMTkxLDE3Myw5LDMsMTMyLDIyMSwyMTMsMTkxLDE3Myw5LDIsMSwxMDAsMA==\"]",
        "version": 15,
        "creationTime": "2024-09-29T21:43:56"
    },
    {
        "id": "c680df86-3d9f-4a24-bb67-b7251d0d6316",
        "docId": "a6a3e4b2-e58e-44f4-ac01-1d25c70223cf",
        "userId": "8e592557-b7e2-4105-8fcd-088538d9d8ea",
        "content": "[\"MSwxLDIyMSwyMTMsMTkxLDE3Myw5LDAsNCwxLDUsMTEzLDExNywxMDUsMTA4LDEwOCwxLDk3LDA=\",\"MSwxLDIyMSwyMTMsMTkxLDE3Myw5LDEsMTMyLDIyMSwyMTMsMTkxLDE3Myw5LDAsMSw5OCww\",\"MSwxLDIyMSwyMTMsMTkxLDE3Myw5LDIsMTMyLDIyMSwyMTMsMTkxLDE3Myw5LDEsMSw5OSww\"]",
        "version": 14,
        "creationTime": "2024-09-29T21:43:53"
    }
]

changes = [
    ['AQH0oOuGDgAEAQVxdWlsbAFoAA==', 'AQH0oOuGDgGE9KDrhg4AAWUA'],
    ['AQH0oOuGDgKE9KDrhg4BAWwA', 'AQH0oOuGDgOE9KDrhg4CAWwA', 'AQH0oOuGDgSE9KDrhg4DAW8A']
]

changes = [
    [
        "AQGLzeLXDRqEi83i1w0ZAW8A",
        "AQGLzeLXDRuEi83i1w0aAW8A",
        "AQGLzeLXDRyEi83i1w0bAW8A",
        "AQGLzeLXDR2Ei83i1w0cAW8A",
        "AQGLzeLXDR6Ei83i1w0dAW8A",
        "AQGLzeLXDR+Ei83i1w0eASAA"
    ],
    [
        "AQH0pfKXBC+E9KXylwQuAWwA",
        "AQH0pfKXBDCE9KXylwQvAWEA"
    ],
    [
        "AQGLzeLXDQWEi83i1w0EAWoA",
        "AQGLzeLXDQaEi83i1w0FAWEA",
        "AQGLzeLXDQeEi83i1w0GAWwA",
        "AQGLzeLXDQiEi83i1w0HAWQA",
        "AQGLzeLXDQmEi83i1w0IAWkA",
        "AQGLzeLXDQqEi83i1w0JAWkA",
        "AQGLzeLXDQuEi83i1w0KAWkA",
        "AQGLzeLXDQyEi83i1w0LAWkA"
    ],
    [
        "AQGLzeLXDTOEi83i1w0yAW8A",
        "AQGLzeLXDTSEi83i1w0zAWwA",
        "AQGLzeLXDTWEi83i1w00AW8A",
        "AQGLzeLXDTaEi83i1w01AWwA",
        "AQGLzeLXDTeEi83i1w02AW8A"
    ],
    [
        "AQH0pfKXBCSE9KXylwQjAXkA",
        "AQH0pfKXBCWE9KXylwQkAWUA",
        "AQH0pfKXBCaE9KXylwQlASAA",
        "AQH0pfKXBCeE9KXylwQmAXQA",
        "AQH0pfKXBCiE9KXylwQnAWgA",
        "AQH0pfKXBCmE9KXylwQoAWkA"
    ],
    [
        "AQH0pfKXBAuE9KXylwQKAWsA",
        "AQH0pfKXBAyE9KXylwQLAWUA",
        "AQH0pfKXBA2E9KXylwQMASAA",
        "AQH0pfKXBA6E9KXylwQNAWEA",
        "AQH0pfKXBA+E9KXylwQOAWEA",
        "AQH0pfKXBBCE9KXylwQPAWcA",
        "AQH0pfKXBBGE9KXylwQQAWUA",
        "AQH0pfKXBBKE9KXylwQRASAA",
        "AQH0pfKXBBOE9KXylwQSAWQA"
    ],
    [
        "AQGLzeLXDSCEi83i1w0fAWIA",
        "AQGLzeLXDSGEi83i1w0gAWgA",
        "AQGLzeLXDSKEi83i1w0hAWEA",
        "AQGLzeLXDSOEi83i1w0iAWkA",
        "AQGLzeLXDSSEi83i1w0jAWkA",
        "AQGLzeLXDSWEi83i1w0kAWkA",
        "AQGLzeLXDSaEi83i1w0lAWkA"
    ],
    [
        "AQGLzeLXDSeEi83i1w0mAWkA",
        "AQGLzeLXDSiEi83i1w0nAWkA",
        "AQGLzeLXDSmEi83i1w0oAWkA",
        "AQGLzeLXDSqEi83i1w0pAWkA",
        "AQGLzeLXDSuEi83i1w0qASAA"
    ],
    [
        "AQGLzeLXDTiEi83i1w03AWwA"
    ],
    [
        "AQGLzeLXDQWEi83i1w0EAWoA",
        "AQGLzeLXDQaEi83i1w0FAWEA",
        "AQGLzeLXDQeEi83i1w0GAWwA",
        "AQGLzeLXDQiEi83i1w0HAWQA",
        "AQGLzeLXDQmEi83i1w0IAWkA",
        "AQGLzeLXDQqEi83i1w0JAWkA",
        "AQGLzeLXDQuEi83i1w0KAWkA",
        "AQGLzeLXDQyEi83i1w0LAWkA",
        "AQGLzeLXDQ2Ei83i1w0MASAA",
        "AQGLzeLXDQ6Ei83i1w0NAWoA",
        "AQGLzeLXDQ+Ei83i1w0OAWEA",
        "AQGLzeLXDRCEi83i1w0PAWwA",
        "AQGLzeLXDRGEi83i1w0QAWQA"
    ],
    [
        "AQGLzeLXDTiEi83i1w03AWwA"
    ],
    [
        "AQH0pfKXBAiE9KXylwQHAQoA",
        "AQH0pfKXBAmE9KXylwQIAWkA",
        "AQH0pfKXBAqE9KXylwQJAXMA"
    ],
    [
        "AQGLzeLXDRKEi83i1w0RAWkA",
        "AQGLzeLXDROEi83i1w0SAWkA",
        "AQGLzeLXDRSEi83i1w0TASAA",
        "AQGLzeLXDRWEi83i1w0UAWwA",
        "AQGLzeLXDRaEi83i1w0VAWkA",
        "AQGLzeLXDReEi83i1w0WAWsA",
        "AQGLzeLXDRiEi83i1w0XAWgA",
        "AQGLzeLXDRmEi83i1w0YAWoA"
    ],
    [
        "AQH0pfKXBCSE9KXylwQjAXkA",
        "AQH0pfKXBCWE9KXylwQkAWUA",
        "AQH0pfKXBCaE9KXylwQlASAA",
        "AQH0pfKXBCeE9KXylwQmAXQA",
        "AQH0pfKXBCiE9KXylwQnAWgA",
        "AQH0pfKXBCmE9KXylwQoAWkA",
        "AQH0pfKXBCqE9KXylwQpAXIA",
        "AQH0pfKXBCuE9KXylwQqAWQA",
        "AQH0pfKXBCyE9KXylwQrASAA",
        "AQH0pfKXBC2E9KXylwQsAXcA",
        "AQH0pfKXBC6E9KXylwQtAWEA"
    ],
    [
        "AAOxlLLxDgEAEYvN4tcNAQA59KXylwQCABobFg=="
    ],
    [
        "AQH0pfKXBBSE9KXylwQTAW8A",
        "AQH0pfKXBBWE9KXylwQUAW8A",
        "AQH0pfKXBBaE9KXylwQVAXMA",
        "AQH0pfKXBBeE9KXylwQWAXIA",
        "AQH0pfKXBBiE9KXylwQXAWEA",
        "AQH0pfKXBBmE9KXylwQYASAA",
        "AQH0pfKXBBqE9KXylwQZAWUA"
    ],
    [
        "AQH0pfKXBBSE9KXylwQTAW8A",
        "AQH0pfKXBBWE9KXylwQUAW8A",
        "AQH0pfKXBBaE9KXylwQVAXMA"
    ],
    [
        "AQH0pfKXBC+E9KXylwQuAWwA",
        "AQH0pfKXBDCE9KXylwQvAWEA"
    ],
    [
        "AQGLzeLXDQGEi83i1w0AAQoA",
        "AQGLzeLXDQKEi83i1w0BAQoA",
        "AQGLzeLXDQOEi83i1w0CAQoA",
        "AQGLzeLXDQSEi83i1w0DAQoA"
    ],
    [
        "AQH0pfKXBACEsZSy8Q4QAXMA",
        "AQH0pfKXBAGE9KXylwQAAWUA"
    ],
    [
        "AQH0pfKXBACEsZSy8Q4QAXMA",
        "AQH0pfKXBAGE9KXylwQAAWUA",
        "AQH0pfKXBAKE9KXylwQBAW4A",
        "AQH0pfKXBAOE9KXylwQCAXQA",
        "AQH0pfKXBASE9KXylwQDAWUA",
        "AQH0pfKXBAWE9KXylwQEAW4A",
        "AQH0pfKXBAaE9KXylwQFAWMA",
        "AQH0pfKXBAeE9KXylwQGAWUA"
    ],
    [
        "AQGLzeLXDQGEi83i1w0AAQoA",
        "AQGLzeLXDQKEi83i1w0BAQoA"
    ],
    [
        "AQGLzeLXDQCE9KXylwQwAQoA"
    ],
    [
        "AQGxlLLxDguEsZSy8Q4KAWYA",
        "AQGxlLLxDgyEsZSy8Q4LAWkA",
        "AQGxlLLxDg2EsZSy8Q4MAXIA",
        "AQGxlLLxDg6EsZSy8Q4NAXMA",
        "AQGxlLLxDg+EsZSy8Q4OAXQA",
        "AQGxlLLxDhCEsZSy8Q4PASAA"
    ],
    [
        "AQH0pfKXBAuE9KXylwQKAWsA",
        "AQH0pfKXBAyE9KXylwQLAWUA",
        "AQH0pfKXBA2E9KXylwQMASAA",
        "AQH0pfKXBA6E9KXylwQNAWEA",
        "AQH0pfKXBA+E9KXylwQOAWEA"
    ],
    [
        "AAH0pfKXBAEaAQ=="
    ],
    [
        "AAH0pfKXBAEaAQ==",
        "AQH0pfKXBBuE9KXylwQaAXMA",
        "AQH0pfKXBByE9KXylwQbAWUA",
        "AQH0pfKXBB2E9KXylwQcAW4A",
        "AQH0pfKXBB6E9KXylwQdAXQA",
        "AQH0pfKXBB+E9KXylwQeAWUA",
        "AQH0pfKXBCCE9KXylwQfAW4A",
        "AQH0pfKXBCGE9KXylwQgAWMA"
    ],
    [
        "AQH0pfKXBCKE9KXylwQhAWUA",
        "AQH0pfKXBCOE9KXylwQiAQoA"
    ],
    [
        "AQGLzeLXDSyEi83i1w0rAWwA",
        "AQGLzeLXDS2Ei83i1w0sAW8A",
        "AQGLzeLXDS6Ei83i1w0tAWwA",
        "AQGLzeLXDS+Ei83i1w0uAW8A",
        "AQGLzeLXDTCEi83i1w0vAWwA",
        "AQGLzeLXDTGEi83i1w0wAW8A",
        "AQGLzeLXDTKEi83i1w0xAWwA"
    ],
    [
        "AQGxlLLxDgAEAQVxdWlsbAFoAA==",
        "AQGxlLLxDgGEsZSy8Q4AAWkA",
        "AQGxlLLxDgKEsZSy8Q4BASAA",
        "AQGxlLLxDgOEsZSy8Q4CAXQA",
        "AQGxlLLxDgSEsZSy8Q4DAWgA",
        "AQGxlLLxDgWEsZSy8Q4EAWkA",
        "AQGxlLLxDgaEsZSy8Q4FASAA",
        "AQGxlLLxDgeEsZSy8Q4GAXMA",
        "AQGxlLLxDgiEsZSy8Q4HAWkA",
        "AQGxlLLxDgmEsZSy8Q4IAXMA",
        "AQGxlLLxDgqEsZSy8Q4JASAA"
    ]
]

let something = ['MSwxLDIxMywyMDMsMjQ1LDE5OSw1LDAsNCwxLDUsMTEzLDExNywxMDUsMTA4LDEwOCwxLDk3LDA=', 'MSwxLDIxMywyMDMsMjQ1LDE5OSw1LDEsMTMyLDIxMywyMDMsMjQ1LDE5OSw1LDAsMSw5OCww', 'MSwxLDIxMywyMDMsMjQ1LDE5OSw1LDIsMTMyLDIxMywyMDMsMjQ1LDE5OSw1LDEsMSw5OSww', 'MSwxLDIxMywyMDMsMjQ1LDE5OSw1LDMsMTMyLDIxMywyMDMsMjQ1LDE5OSw1LDIsMSwxMDAsMA==']

export { exampleDocuments, changes }

