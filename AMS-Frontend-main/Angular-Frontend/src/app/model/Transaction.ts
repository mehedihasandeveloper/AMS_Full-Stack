export interface Transaction {
    id: any,
    userName: string,
    entryDate: string,
    amount: number,
    debitAccount: string,
    creditAccount: string,
    description: string;
    receipt: string;
}