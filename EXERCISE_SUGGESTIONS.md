# Additional ISP Exercise Suggestions

This document outlines several fun exercise ideas that follow the same pattern as the Smart Home exercise. Each would demonstrate Interface Segregation Principle violations in different domains.

## 1. Multi-Media Player Exercise

### Domain
Media playback and streaming systems

### ISP Violation
A single `MediaPlayerService` interface combines:
- Audio playback (play, pause, stop, volume control)
- Video playback (play video, adjust quality)
- Streaming (start stream, check connection)
- Download management (download, pause download, check progress)

### Problem
- A simple MP3 player is forced to implement video/streaming methods
- A video player doesn't need download management
- A streaming device doesn't need local download methods

### Suggested Interfaces After Refactoring
- `AudioPlayer` - audio playback methods
- `VideoPlayer` - video playback methods
- `StreamingService` - streaming methods
- `DownloadManager` - download management methods

### Device Types
- `MP3Player` - implements `AudioPlayer`
- `VideoPlayerDevice` - implements `VideoPlayer`, `AudioPlayer`
- `StreamingBox` - implements `StreamingService`, `VideoPlayer`, `AudioPlayer`
- `MediaDownloader` - implements `DownloadManager`

---

## 2. Employee Management System Exercise

### Domain
Human Resources and employee management

### ISP Violation
A single `EmployeeService` interface combines:
- Basic employee info (getName, getEmployeeId, getDepartment)
- Salary management (getSalary, setSalary, calculateBonus)
- Time tracking (clockIn, clockOut, getHoursWorked)
- Managerial duties (approveLeave, reviewPerformance, assignTasks)
- Contractor management (getContractEndDate, renewContract)

### Problem
- Hourly workers don't need salary management methods
- Contractors don't need time tracking or managerial methods
- Managers don't need contractor-specific methods
- Interns don't need salary or managerial methods

### Suggested Interfaces After Refactoring
- `EmployeeInfo` - basic employee information
- `SalariedEmployee` - salary management methods
- `TimeTrackable` - time tracking methods
- `Managerial` - management and oversight methods
- `ContractorManagement` - contractor-specific methods

### Employee Types
- `HourlyEmployee` - implements `EmployeeInfo`, `TimeTrackable`
- `SalariedEmployee` - implements `EmployeeInfo`, `SalariedEmployee`
- `Manager` - implements `EmployeeInfo`, `SalariedEmployee`, `Managerial`
- `Contractor` - implements `EmployeeInfo`, `ContractorManagement`
- `Intern` - implements `EmployeeInfo`

---

## 3. Office Equipment Exercise

### Domain
Office equipment and multi-function devices

### ISP Violation
A single `OfficeDeviceService` interface combines:
- Printing (print, cancelPrint, getPrintQueue)
- Scanning (scan, scanToEmail, scanToFile)
- Faxing (sendFax, receiveFax, checkFaxStatus)
- Copying (copy, setCopies, setColorMode)

### Problem
- A simple printer doesn't need scanning/faxing methods
- A scanner doesn't need printing/faxing methods
- A fax machine doesn't need scanning methods
- Only multi-function devices need all capabilities

### Suggested Interfaces After Refactoring
- `Printer` - printing methods
- `Scanner` - scanning methods
- `FaxMachine` - faxing methods
- `Copier` - copying methods

### Device Types
- `SimplePrinter` - implements `Printer`
- `DocumentScanner` - implements `Scanner`
- `FaxMachine` - implements `FaxMachine`
- `MultiFunctionDevice` - implements `Printer`, `Scanner`, `FaxMachine`, `Copier`

---

## 4. Social Media Platform Exercise

### Domain
Social media and content platforms

### ISP Violation
A single `SocialMediaService` interface combines:
- Content creation (createPost, uploadVideo, startLiveStream)
- Interaction (like, comment, share)
- Messaging (sendMessage, createGroup, checkNotifications)
- Moderation (flagContent, banUser, reviewReports)
- Analytics (getViews, getEngagement, generateReport)

### Problem
- Read-only users don't need content creation methods
- Regular users don't need moderation methods
- Content creators don't need moderation methods
- Moderators don't need analytics methods

### Suggested Interfaces After Refactoring
- `ContentCreator` - content creation methods
- `SocialInteraction` - like, comment, share methods
- `Messaging` - messaging and notification methods
- `Moderation` - content moderation methods
- `Analytics` - analytics and reporting methods

### User Types
- `RegularUser` - implements `SocialInteraction`, `Messaging`
- `ContentCreator` - implements `ContentCreator`, `SocialInteraction`, `Messaging`, `Analytics`
- `Moderator` - implements `SocialInteraction`, `Messaging`, `Moderation`
- `ReadOnlyUser` - implements `SocialInteraction` (no creation)

---

## 5. Payment Processing Exercise

### Domain
Payment and financial transaction systems

### ISP Violation
A single `PaymentService` interface combines:
- Credit card processing (processCreditCard, refundCreditCard)
- Bank transfer (initiateTransfer, verifyAccount)
- Cryptocurrency (sendCrypto, receiveCrypto, checkBalance)
- Invoice management (createInvoice, sendInvoice, markPaid)
- Subscription billing (createSubscription, cancelSubscription, renewSubscription)

### Problem
- Credit card processors don't need crypto methods
- Crypto wallets don't need invoice management
- Invoice systems don't need subscription billing
- Subscription services don't need crypto methods

### Suggested Interfaces After Refactoring
- `CreditCardProcessor` - credit card methods
- `BankTransfer` - bank transfer methods
- `CryptocurrencyWallet` - crypto methods
- `InvoiceManager` - invoice methods
- `SubscriptionBilling` - subscription methods

### Payment Types
- `CreditCardGateway` - implements `CreditCardProcessor`
- `BankAccount` - implements `BankTransfer`
- `CryptoWallet` - implements `CryptocurrencyWallet`
- `InvoiceSystem` - implements `InvoiceManager`
- `SubscriptionService` - implements `SubscriptionBilling`, `CreditCardProcessor`

---

## Implementation Pattern

Each exercise should follow the same structure:

1. **Service Interface** (`{Domain}Service.java`) - UNTOUCHED contract
2. **Main Controller** (`{Domain}Controller.java`) - Violates ISP
3. **Data Classes** - Simple POJOs representing entities
4. **Integration Tests** - Verify public API contract
5. **Exercise Documentation** - Detailed refactoring instructions

## Benefits of These Exercises

- **Different Domains**: Students see ISP violations in various contexts
- **Real-World Relevance**: Each domain reflects actual software design challenges
- **Progressive Difficulty**: Some exercises are simpler (Office Equipment) than others (Social Media)
- **Clear Violations**: Each clearly demonstrates why ISP matters
- **Practical Application**: Students learn to recognize and fix ISP violations

---

**Note**: These are suggestions for future exercises. The Smart Home exercise is fully implemented and ready to use!

